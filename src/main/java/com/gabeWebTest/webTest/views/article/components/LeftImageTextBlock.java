package com.gabeWebTest.webTest.views.article.components;

import com.gabeWebTest.webTest.utils.ImageLoader;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.GET_SOURCE_ID;
import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.REMOVE_PATTERN;

public class LeftImageTextBlock extends Div {


    public LeftImageTextBlock(String paragraph, ImageLoader imageLoader) {
        super();
        int sourceID = GET_SOURCE_ID(paragraph);
        Image sourceImage = imageLoader.LOAD_IMAGE(sourceID);
        sourceImage.addClassName("left-image");
        paragraph = REMOVE_PATTERN(paragraph);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        paragraphToAdd.addClassName("article-text");
        add(sourceImage);
        add(paragraphToAdd);
    }
}
