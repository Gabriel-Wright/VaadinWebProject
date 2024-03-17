package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.*;

public class ArticleTextBlockGenerator {

    private final WebPage webPage;
    private String articleRawText;
    private VerticalLayout textBlock;

    public ArticleTextBlockGenerator(WebPage webPage) {
        this.webPage = webPage;
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
        paragraph = REMOVE_PATTERN(paragraph);
        layout.add(new Paragraph(paragraph));
        return layout;
    }

    private VerticalLayout loadVerticalParagraph(String paragraph) {
        VerticalLayout layout = new VerticalLayout();
        int sourceID = GET_SOURCE_ID(paragraph);
        paragraph = REMOVE_PATTERN(paragraph);
        layout.add(new Paragraph(paragraph));
        return layout;
    }
}
