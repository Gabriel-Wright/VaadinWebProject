package com.gabeWebTest.webTest.views.article.components;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.*;

public class YoutubeSourceTextBlock extends VerticalLayout {

    public YoutubeSourceTextBlock(String paragraph) {
        String url = GET_PATTERN_STRING(paragraph);
        add(createYoutubeEmbed(url));
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = LOAD_PARAGRAPH_WITH_LINKS(paragraph);
        paragraphToAdd.addClassName("article-text");
        add(paragraphToAdd);

    }

    private Div createYoutubeEmbed(String url) {
        Div youtubeContainer = new Div();
        youtubeContainer.addClassName("youtube-container");
        String iframeHtml = "<iframe width=\"560\" height=\"315\" src=\""+url+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        // Create an Html component and set the HTML content
        Html html = new Html(iframeHtml);
        html.addClassName("youtube-embed");

        youtubeContainer.add(html);

        return youtubeContainer;
    }

}
