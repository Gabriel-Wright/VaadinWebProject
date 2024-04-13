package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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
        articleRawText = webPage.getArticlePreviewText().getArticleTextAsString();
        separateAndFormatParagraphs();
        return textBlock;
    }

    private void separateAndFormatParagraphs() {
        String[] paragraphArray = SPLIT_TEXT(articleRawText);
        textBlock = new VerticalLayout();
        textBlock.addClassName("article-text-block");

        for(String paragraphText: paragraphArray) {
            textBlock.add(loadParagraphByFormat(paragraphText));
        }
    }

    private Component loadParagraphByFormat(String paragraph) {
        int paragraphFormat = GET_FORMAT(paragraph);
        return switch(paragraphFormat) {
            case HORIZONTAL_LEFT -> loadHorizontalLeftParagraph(paragraph);
            case HORIZONTAL_RIGHT -> loadHorizontalRightParagraph(paragraph);
            case VERTICAL -> loadVerticalParagraph(paragraph);
            default -> loadNoPictureParagraph(paragraph);
        };
    }

    private VerticalLayout loadNoPictureParagraph(String paragraph) {
        VerticalLayout layout = new VerticalLayout();
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        layout.add(paragraphToAdd);
        return layout;
    }

    private Div loadHorizontalRightParagraph(String paragraph) {
        Div layout = new Div();
        int sourceID = GET_SOURCE_ID(paragraph);
        Image sourceImage = loadImage(sourceID);
        sourceImage.addClassName("right-image");
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        layout.add(sourceImage);
        layout.add(paragraphToAdd);
        return layout;
    }

    private Div loadHorizontalLeftParagraph(String paragraph) {
        Div layout = new Div();
        int sourceID = GET_SOURCE_ID(paragraph);
        Image sourceImage = loadImage(sourceID);
        sourceImage.addClassName("left-image");
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        layout.add(sourceImage);
        layout.add(paragraphToAdd);
        return layout;
    }

    private VerticalLayout loadVerticalParagraph(String paragraph) {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        int sourceID = GET_SOURCE_ID(paragraph);
        Image sourceImage = loadImage(sourceID);
        sourceImage.addClassName("vertical-image");
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        layout.add(sourceImage);
        layout.add(paragraphToAdd);
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
