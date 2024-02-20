package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableSupplier;

public class WebPageListRenderer extends ComponentRenderer<HorizontalLayout, WebPage> {

    public WebPageListRenderer(SerializableSupplier<HorizontalLayout> componentSupplier) {
        super(componentSupplier);
    }

    @Override
    public HorizontalLayout createComponent(WebPage item) {
        // Create components to represent a single item
        Image image = new Image("img/LI-In-Bug.png", "ArticleImage");
//        image.setWidth("100px"); // Set image width


        VerticalLayout verticalText = new VerticalLayout();
        // You can add more components here to display other details of the web page
        Span componentTitle = new Span(item.getTitle());
        componentTitle.addClassName("larger-font");
        String title = item.getTitle();
        // Calculate the width of the title text
        verticalText.setWidth("60%"); // Ensure the layout takes up the available width

        Paragraph paragraph = new Paragraph("This is preview text dear god please work");
        verticalText.add(image, componentTitle, paragraph);

        //Paragraph articleText = new Paragraph(item.getArticleText().getArticleText());
        // Create a vertical layout to contain the components
        HorizontalLayout layout = new HorizontalLayout(verticalText);
        layout.setWidthFull(); // Set layout width to fill the available space
        layout.setAlignItems(FlexComponent.Alignment.CENTER); // Center-align items vertically

        // Add a CSS class for styling
        layout.addClassName("hover-effect");

        return layout;
    }

}
