package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableSupplier;

public class WebPageRenderer extends ComponentRenderer<HorizontalLayout, WebPage> {

    public WebPageRenderer(SerializableSupplier<HorizontalLayout> componentSupplier) {
        super(componentSupplier);
    }

    @Override
    public HorizontalLayout createComponent(WebPage item) {
        // Create components to represent a single item
        Image image = new Image("img/LI-In-Bug.png", "ArticleImage");
        image.setWidth("100px"); // Set image width

        // You can add more components here to display other details of the web page
        Span componentTitle = new Span(item.getTitle());
//        Paragraph articleText = new Paragraph(item.getArticleText().getArticleText());
        // Create a vertical layout to contain the components
        HorizontalLayout layout = new HorizontalLayout(image, componentTitle);
        layout.setWidth("100%"); // Set layout width

        return layout;

    }

}
