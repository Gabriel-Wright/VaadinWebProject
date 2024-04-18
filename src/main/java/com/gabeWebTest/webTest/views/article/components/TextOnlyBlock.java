package com.gabeWebTest.webTest.views.article.components;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.LOAD_PARAGRAPH_WITH_LINKS;
import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.REMOVE_PATTERN;

public class TextOnlyBlock extends VerticalLayout {

    public TextOnlyBlock(String paragraph) {
        super();
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = LOAD_PARAGRAPH_WITH_LINKS(paragraph);
        paragraphToAdd.addClassName("article-text");
        add(paragraphToAdd);
    }
}
