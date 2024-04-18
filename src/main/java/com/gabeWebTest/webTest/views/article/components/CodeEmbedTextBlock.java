package com.gabeWebTest.webTest.views.article.components;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.*;

public class CodeEmbedTextBlock extends VerticalLayout {

    public CodeEmbedTextBlock(String paragraph) {
        String code = GET_PATTERN_STRING(paragraph);
        add(createCodeHtml(code));
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = LOAD_PARAGRAPH_WITH_LINKS(paragraph);
        paragraphToAdd.addClassName("article-text");
        add(paragraphToAdd);
    }

    private Html createCodeHtml(String code) {
        String formattedCode = "<pre><code>" + code +"</code></pre>";
        Html codeHtml = new Html(formattedCode);
        codeHtml.addClassName("code-style");
        return codeHtml;
    }

}
