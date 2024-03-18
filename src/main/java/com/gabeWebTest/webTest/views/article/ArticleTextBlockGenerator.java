package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Optional;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.*;

public class ArticleTextBlockGenerator {

    private final WebPage webPage;
    private String articleRawText;
    private VerticalLayout textBlock;
    private VisualSourceService visualSourceService;

    private final String DEFAULT_IMAGE_PATH = "img/thumbnails/memoriesOfMurderThumbnail.jpg";

    public ArticleTextBlockGenerator(WebPage webPage, VisualSourceService visualSourceService) {
        this.webPage = webPage;
        this.visualSourceService = visualSourceService;
    }

    public VerticalLayout loadArticleTextBlock() {
        articleRawText = webPage.getArticleText().getArticleTextAsString();
        separateAndFormatParagraphs();
        return textBlock;
    }

    private void separateAndFormatParagraphs() {
        String[] paragraphArray = SPLIT_TEXT(articleRawText);
        textBlock = new VerticalLayout();

        for(String paragraphText: paragraphArray) {
            textBlock.add(loadParagraphByFormat(paragraphText));
        }
    }

    private Component loadParagraphByFormat(String paragraph) {
        int paragraphFormat = GET_FORMAT(paragraph);
        return switch(paragraphFormat) {
            case HORIZONTAL -> loadHorizontalParagraph(paragraph);
            default -> loadVerticalParagraph(paragraph);
        };
    }

    private HorizontalLayout loadHorizontalParagraph(String paragraph) {
        HorizontalLayout layout = new HorizontalLayout();
        int sourceID = GET_SOURCE_ID(paragraph);
        Image sourceImage = loadImage(sourceID);
        sourceImage.setWidth("50%");
        paragraph = REMOVE_PATTERN(paragraph);
        layout.add(new Paragraph(paragraph));
        layout.add(sourceImage);
        return layout;
    }

    private VerticalLayout loadVerticalParagraph(String paragraph) {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        int sourceID = GET_SOURCE_ID(paragraph);
        Image sourceImage = loadImage(sourceID);
        sourceImage.setWidth("70%");
        sourceImage.getStyle().set("margin","auto");
        paragraph = REMOVE_PATTERN(paragraph);
        layout.add(new Paragraph(paragraph));
        layout.add(sourceImage);
        return layout;
    }

    private Image loadImage(int sourceID) {
        Optional<VisualSource> visualSourceOptional = visualSourceService.findVisualSource(sourceID);
        String imagePath;
        if(visualSourceOptional.isPresent()) {
            imagePath = visualSourceOptional.get().getImagePath();
        } else {
            imagePath =DEFAULT_IMAGE_PATH;
        }
        return new Image(imagePath, imagePath);
    }
}
