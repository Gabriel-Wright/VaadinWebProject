package com.gabeWebTest.webTest.views.article.components;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.GET_PATTERN_STRING;
import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.REMOVE_PATTERN;

public class YoutubeSourceTextBlock extends VerticalLayout {

    public YoutubeSourceTextBlock(String paragraph) {
        String url = GET_PATTERN_STRING(paragraph);
        add(createYoutubeEmbed(url));
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        add(paragraphToAdd);

    }

    private Html createYoutubeEmbed(String url) {
        String iframeHtml = "<iframe width=\"560\" height=\"315\" src=\""+url+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        // Create an Html component and set the HTML content
        Html html = new Html(iframeHtml);
        html.addClassName("youtube-embed");
        html.getStyle().set("margin","auto");

        return html;
    }

}
