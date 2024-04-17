package com.gabeWebTest.webTest.views.article;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.GET_PATTERN_STRING;
import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.REMOVE_PATTERN;

public class HeadingAndTextBlock extends Div {
    public HeadingAndTextBlock(String paragraph) {
        super();
        String header = GET_PATTERN_STRING(paragraph);
        H1 titleOfArticle = new H1(header);
        titleOfArticle.addClassName("article-text-block-title");
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        add(titleOfArticle);
        add(paragraphToAdd);
    }
}
