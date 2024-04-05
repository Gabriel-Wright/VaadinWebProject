package com.gabeWebTest.webTest.views.dashboard.content;

import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.views.article.ArticleView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableSupplier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.REMOVE_PATTERN;
import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.SPLIT_TEXT;

public class WebPageListRenderer extends ComponentRenderer<Div, WebPage> {

    public WebPageListRenderer(SerializableSupplier<Div> componentSupplier) {
        super(componentSupplier);
    }

    private Div layout;
    @Override
    public Div createComponent(WebPage item) {
        // Create components to represent a single item
        Div verticalText = loadWebPageVerticalText(item);
        Image image = loadWebPagePreviewImage(item);

        layout = new Div(image, verticalText);

        layout.addClassName("article-style");

        layout.addClickListener(event -> {
            UI.getCurrent().navigate(ArticleView.class, item.getId());
        });
        return layout;
    }

    @Transactional
    private Div createTagDisplayComponent(WebPage item) {
        Set<Tag> tags = item.getTags();
        Div horizontalLayout = new Div();
//        horizontalLayout.addClassName("tag-ball-style");
//        horizontalLayout.getStyle().set("flex-wrap","wrap");
//        horizontalLayout.setPadding(false); // Disable padding to minimize extra space

        for(Tag tag: tags) {
            horizontalLayout.add(tag.createTagComponent());
        }
        return horizontalLayout;
    }

    private Image loadWebPagePreviewImage(WebPage item) {
        Image image;
        if(item.getThumbnailPath()==null) {
            image = new Image("img/LI-In-Bug.png", "ArticleImage");
        } else {
            image = new Image(item.getThumbnailPath(),item.getTitle());
        }
        image.addClassName("image-preview-container");
        return image;
    }

    private Div loadWebPageVerticalText(WebPage item) {
        Div verticalText = new Div();
        verticalText.addClassName("article-preview-block");
        H1 componentTitle = loadWebPagePreviewTitle(item);
        Paragraph paragraph = loadWebPagePreviewParagraph(item);
        Div tagComponent = loadWebPageTags(item);
        Paragraph articleDate = loadWebPageDate(item);
//        verticalText.add(componentTitle);
//        verticalText.add(articleDate);
//        verticalText.add(paragraph);
        verticalText.add(componentTitle, articleDate,paragraph, tagComponent);
//        verticalText.setSizeFull();
//        verticalText.setWidth("30%");

        return verticalText;
    }

    private H1 loadWebPagePreviewTitle(WebPage item) {
        H1 componentTitle = new H1(item.getTitle());
        componentTitle.addClassName("adaptive-title-font");
        return componentTitle;
    }

    private Paragraph loadWebPagePreviewParagraph(WebPage item) {
        //Maybe have to add a preview text bit to the DB
        String webPageFirstParagraph = SPLIT_TEXT(REMOVE_PATTERN(item.getArticleText().getArticleTextAsString()))[0];

        Paragraph paragraph = new Paragraph(webPageFirstParagraph);
        paragraph.addClassName("article-preview-text");

        return paragraph;
    }

    private Div loadWebPageTags(WebPage item) {
        Div tagsComponent = createTagDisplayComponent(item);
//        tagsComponent.setAlignItems(FlexComponent.Alignment.START);
//        tagsComponent.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        return tagsComponent;
    }

    private Paragraph loadWebPageDate(WebPage item) {
        LocalDate webPageCreationDate = item.getDates().getTimeCreated().toLocalDate();
        LocalTime webPageCreationTime = item.getDates().getTimeCreated().toLocalTime();
        LocalDate webPageLastUpdateDate = item.getDates().getTimeLastUpdated().toLocalDate();
        LocalTime webPageLastUpdateTime = item.getDates().getTimeLastUpdated().toLocalTime();

        Paragraph articleDate = new Paragraph(webPageCreationDate.toString());
        articleDate.addClassName("date-style");

        String dateTimeCreated = "Article created:" +webPageCreationDate.toString() +" " +webPageCreationTime.toString();
        String dateLastUpdated = "Article last Updated:" + webPageLastUpdateDate.toString() + " " + webPageLastUpdateTime.toString();
        Tooltip dateTip = Tooltip.forComponent(articleDate).
                withText(dateTimeCreated +"\n"+dateLastUpdated).
                withPosition(Tooltip.TooltipPosition.BOTTOM);

        return articleDate;
    }

    public Div getLayout() {
        return layout;
    }
}
