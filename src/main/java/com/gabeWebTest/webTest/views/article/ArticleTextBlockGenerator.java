package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        if(paragraph.contains("<") && paragraph.contains(">")) {
            int startIndex = paragraph.indexOf("<") + 1;
            int endIndex = paragraph.indexOf(">");
            String indicator = paragraph.substring(startIndex, endIndex);

            //Remove indicator from paragraph

            paragraph = paragraph.replace("<" + indicator + ">", "");
            System.out.println(paragraph);
        }
        return null;
    }
}
