package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.views.article.ArticleView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.router.QueryParameters;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class WebPageListRenderer extends ComponentRenderer<HorizontalLayout, WebPage> {

    public WebPageListRenderer(SerializableSupplier<HorizontalLayout> componentSupplier) {
        super(componentSupplier);
    }

    private HorizontalLayout layout;
    @Override
    public HorizontalLayout createComponent(WebPage item) {
        // Create components to represent a single item
        Image image = loadWebPagePreviewImage(item);
        VerticalLayout verticalText = loadWebPageVerticalText(item);

        layout = new HorizontalLayout(verticalText, image);
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setWidthFull();

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layout.setAlignItems(FlexComponent.Alignment.START);
        layout.expand(verticalText);

        layout.addClassName("article-style");

        layout.addClickListener(event -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("webPageId", String.valueOf(item.getId()));
            UI.getCurrent().navigate(ArticleView.class, QueryParameters.simple(parameters));
        });
        return layout;
    }

    @Transactional
    private HorizontalLayout createTagDisplayComponent(WebPage item) {
        Set<Tag> tags = item.getTags();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.getStyle().set("flex-wrap","wrap");
        horizontalLayout.setPadding(false); // Disable padding to minimize extra space

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
        image.addClassName("image-container");
        return image;
    }

    private VerticalLayout loadWebPageVerticalText(WebPage item) {
        VerticalLayout verticalText = new VerticalLayout();
        H2 componentTitle = loadWebpPagePreviewTitle(item);

        Paragraph paragraph = loadWebpagePreviewParagraph(item);
        HorizontalLayout tagComponent = loadWebPageTags(item);
        Span articleDate = loadWebPageDate(item);

        verticalText.add(componentTitle, articleDate,paragraph, tagComponent);
        verticalText.setPadding(false);
        verticalText.setSpacing(false);
        verticalText.setSizeFull();
        verticalText.setWidth("30%");

        return verticalText;
    }

    private H2 loadWebpPagePreviewTitle(WebPage item) {
        H2 componentTitle = new H2(item.getTitle());
        componentTitle.addClassName("adaptive-title-font");
        return componentTitle;
    }

    private Paragraph loadWebpagePreviewParagraph(WebPage item) {
        Paragraph paragraph = new Paragraph("This is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThisThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThisThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThisThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please work");
        paragraph.addClassName("article-preview-text");

        return paragraph;
    }

    private HorizontalLayout loadWebPageTags(WebPage item) {
        HorizontalLayout tagsComponent = createTagDisplayComponent(item);
        tagsComponent.setAlignItems(FlexComponent.Alignment.START);
        tagsComponent.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        return tagsComponent;
    }

    private Span loadWebPageDate(WebPage item) {
        LocalDate webPageCreationDate = item.getDates().getTimeCreated().toLocalDate();
        LocalTime webPageCreationTime = item.getDates().getTimeCreated().toLocalTime();
        LocalDate webPageLastUpdateDate = item.getDates().getTimeLastUpdated().toLocalDate();
        LocalTime webPageLastUpdateTime = item.getDates().getTimeLastUpdated().toLocalTime();

        Span articleDate = new Span(webPageCreationDate.toString());
        articleDate.addClassName("date-style");

        String dateTimeCreated = "Article created:" +webPageCreationDate.toString() +" " +webPageCreationTime.toString();
        String dateLastUpdated = "Article last Updated:" + webPageLastUpdateDate.toString() + " " + webPageLastUpdateTime.toString();
        Tooltip dateTip = Tooltip.forComponent(articleDate).
                withText(dateTimeCreated +"\n"+dateLastUpdated).
                withPosition(Tooltip.TooltipPosition.BOTTOM);

        return articleDate;
    }

    public HorizontalLayout getLayout() {
        return layout;
    }
}
