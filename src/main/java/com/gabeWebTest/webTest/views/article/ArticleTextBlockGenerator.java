package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.*;

public class ArticleTextBlockGenerator {

    private final WebPage webPage;
    private String articleRawText;
    private List<Component> paragraphBlocks;

    public ArticleTextBlockGenerator(WebPage webPage) {
        this.webPage = webPage;
    }

    public VerticalLayout loadArticleTextBlock() {
        VerticalLayout layout = new VerticalLayout();
        articleRawText = webPage.getArticleText().getArticleTextAsString();
        separateParagraphs();
        return layout;
    }

    private void separateParagraphs() {
        String[] paragraphArray = articleRawText.split("\\n");
        paragraphBlocks = new ArrayList<>();

        for(String paragraphText: paragraphArray) {
            paragraphBlocks.add(loadParagraph(paragraphText));
        }
    }

    private Component loadParagraph(String paragraph) {
        int paragraphFormat = GET_FORMAT(paragraph);
        return switch(paragraphFormat) {
            case HORIZONTAL -> loadHorizontalParagraph(paragraph);
            default -> loadVerticalParagraph(paragraph);
        };
    }

    private HorizontalLayout loadHorizontalParagraph(String paragraph) {
        int sourceID = GET_SOURCE_ID(paragraph);
        paragraph = REMOVE_PATTERN(paragraph);
        return null;
    }

    private VerticalLayout loadVerticalParagraph(String paragraph) {
        int sourceID = GET_SOURCE_ID(paragraph);
        paragraph = REMOVE_PATTERN(paragraph);

        return null;
    }
}
