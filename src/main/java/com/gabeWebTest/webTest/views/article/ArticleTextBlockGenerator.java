package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.gabeWebTest.webTest.utils.ImageLoader;
import com.gabeWebTest.webTest.utils.TxtFileParser;
import com.gabeWebTest.webTest.views.article.components.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
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
    private ImageLoader imageLoader;


    private final String DEFAULT_IMAGE_PATH = "img/thumbnails/memoriesOfMurderThumbnail.jpg";

    public ArticleTextBlockGenerator(WebPage webPage, VisualSourceService visualSourceService, ImageLoader imageLoader) {
        this.webPage = webPage;
        this.visualSourceService = visualSourceService;
        this.imageLoader = imageLoader;
    }

    public VerticalLayout loadArticleTextBlock() {
        articleRawText = TxtFileParser.readTextFromFile(webPage.getArticleTextPath());
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
            case HORIZONTAL_LEFT -> new LeftImageTextBlock(paragraph, imageLoader);
            case HORIZONTAL_RIGHT -> new RightImageTextBlock(paragraph, imageLoader);
            case VERTICAL -> new VerticalImageTextBlock(paragraph, imageLoader);
            case YOUTUBE_EMBED -> new YoutubeSourceTextBlock(paragraph);
            case CODE_EMBED -> new CodeEmbedTextBlock(paragraph);
            case HEADING -> new HeadingAndTextBlock(paragraph);
            default -> new TextOnlyBlock(paragraph);
        };
    }

//    private VerticalLayout loadCodeEmbed(String paragraph) {
//        VerticalLayout layout = new VerticalLayout();
//        String code = GET_PATTERN_STRING(paragraph);
//        layout.add(createCodeHtml(code));
//        paragraph = REMOVE_PATTERN(paragraph);
//        Paragraph paragraphToAdd = new Paragraph(paragraph);
//        paragraphToAdd.addClassName("article-text");
//        layout.add(paragraphToAdd);
//
//        return layout;
//    }
//
//    private VerticalLayout loadYoutubeEmbed(String paragraph) {
//        VerticalLayout layout = new VerticalLayout();
//        String url = GET_PATTERN_STRING(paragraph);
//        layout.add(createYoutubeEmbed(url));
//        paragraph = REMOVE_PATTERN(paragraph);
//        Paragraph paragraphToAdd = new Paragraph(paragraph);
//        paragraphToAdd.addClassName("article-text");
//        layout.add(paragraphToAdd);
//
//        return layout;
//    }
//
//    private VerticalLayout loadNoPictureParagraph(String paragraph) {
//        VerticalLayout layout = new VerticalLayout();
//        paragraph = REMOVE_PATTERN(paragraph);
//        Paragraph paragraphToAdd = new Paragraph(paragraph);
//        paragraphToAdd.addClassName("article-text");
//        layout.add(paragraphToAdd);
//        return layout;
//    }
//
//    private Div loadHorizontalRightParagraph(String paragraph) {
//        Div layout = new Div();
//        int sourceID = GET_SOURCE_ID(paragraph);
//        Image sourceImage = loadImage(sourceID);
//        sourceImage.addClassName("right-image");
//        paragraph = REMOVE_PATTERN(paragraph);
//        Paragraph paragraphToAdd = new Paragraph(paragraph);
//        paragraphToAdd.addClassName("article-text");
//        layout.add(sourceImage);
//        layout.add(paragraphToAdd);
//        return layout;
//    }
//
//    private Div loadHorizontalLeftParagraph(String paragraph) {
//        Div layout = new Div();
//        int sourceID = GET_SOURCE_ID(paragraph);
//        Image sourceImage = loadImage(sourceID);
//        sourceImage.addClassName("left-image");
//        paragraph = REMOVE_PATTERN(paragraph);
//        Paragraph paragraphToAdd = new Paragraph(paragraph);
//        paragraphToAdd.addClassName("article-text");
//        layout.add(sourceImage);
//        layout.add(paragraphToAdd);
//        return layout;
//    }
//
//    private VerticalLayout loadVerticalParagraph(String paragraph) {
//        VerticalLayout layout = new VerticalLayout();
//        layout.setPadding(false);
//        int sourceID = GET_SOURCE_ID(paragraph);
//        Image sourceImage = loadImage(sourceID);
//        sourceImage.addClassName("vertical-image");
//        paragraph = REMOVE_PATTERN(paragraph);
//        Paragraph paragraphToAdd = new Paragraph(paragraph);
//        paragraphToAdd.addClassName("article-text");
//        layout.add(sourceImage);
//        layout.add(paragraphToAdd);
//        return layout;
//    }
//
//    private Image loadImage(int sourceID) {
//        Optional<VisualSource> visualSourceOptional = visualSourceService.findVisualSource(sourceID);
//        String imagePath;
//        if(visualSourceOptional.isPresent()) {
//            imagePath = visualSourceOptional.get().getImagePath();
//        } else {
//            imagePath =DEFAULT_IMAGE_PATH;
//        }
//        return new Image(imagePath, imagePath);
//    }
//
//    private Html createYoutubeEmbed(String url) {
//        String iframeHtml = "<iframe width=\"560\" height=\"315\" src=\""+url+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
//        // Create an Html component and set the HTML content
//        Html html = new Html(iframeHtml);
//        html.addClassName("youtube-embed");
//        html.getStyle().set("margin","auto");
//
//        return html;
//    }
//
//    private Html createCodeHtml(String code) {
//        String formattedCode = "<pre><code>" + code +"</code></pre>";
//        Html codeHtml = new Html(formattedCode);
//        codeHtml.addClassName("code-style");
//        return codeHtml;
//    }
}
