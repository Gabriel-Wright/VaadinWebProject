package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableSupplier;

public class WebPageRenderer extends ComponentRenderer<VerticalLayout, WebPage> {

    public WebPageRenderer(SerializableSupplier<VerticalLayout> componentSupplier) {
        super(componentSupplier);
    }

    @Override
    public VerticalLayout createComponent(WebPage item) {
        // Create components to represent a single item
        Image image = new Image("img/LI-In-Bug.png", "ArticleImage");
        image.setWidth("100px"); // Set image width

        // You can add more components here to display other details of the web page
        Span componentTitle = new Span(item.getTitle());
//        Paragraph articleText = new Paragraph(item.getArticleText().getArticleText());
        // Create a vertical layout to contain the components
        VerticalLayout layout = new VerticalLayout(image, componentTitle);
        layout.setWidth("100%"); // Set layout width

        return layout;

    }

}
