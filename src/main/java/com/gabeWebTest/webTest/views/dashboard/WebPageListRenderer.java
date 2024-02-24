package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableSupplier;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public class WebPageListRenderer extends ComponentRenderer<HorizontalLayout, WebPage> {

    public WebPageListRenderer(SerializableSupplier<HorizontalLayout> componentSupplier) {
        super(componentSupplier);
    }

    @Override
    public HorizontalLayout createComponent(WebPage item) {
        // Create components to represent a single item
        Image image;
        if(item.getThumbnailPath()==null) {
            image = new Image("img/LI-In-Bug.png", "ArticleImage");
        } else {
            image = new Image(item.getThumbnailPath(),item.getTitle());
        }
        image.addClassName("image-container");

//        image.setWidth("70%"); // Set image width to 70% of parent container
//        image.setHeight("auto"); // Maintain aspect ratio

        VerticalLayout verticalText = new VerticalLayout();
        // You can add more components here to display other details of the web page
        Span componentTitle = new Span(item.getTitle());
        componentTitle.addClassName("adaptive-title-font");
        String title = item.getTitle();
        // Calculate the width of the title text

        Paragraph paragraph = new Paragraph("This is preview text dear god please work");
        verticalText.add(componentTitle, paragraph, createTagDisplayComponent(item));
        verticalText.setPadding(false);
        verticalText.setSpacing(false);
        componentTitle.setHeight("30%");
        paragraph.setHeight("20%");
        verticalText.setAlignItems(FlexComponent.Alignment.CENTER);
//        // Set alignment to top for the VerticalLayout
//         verticalText.setAlignItems(FlexComponent.Alignment.START);
        //Publication date and tags
        Text publicationText = new Text("Published on" + item.getDates().getTimeCreated());

        //Paragraph articleText = new Paragraph(item.getArticleText().getArticleText());
        // Create a vertical layout to contain the components
        HorizontalLayout layout = new HorizontalLayout(verticalText, image);
        // verticalText.setWidth("70%"); // Ensure the layout takes up the available width
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setWidthFull();
        image.setWidth("67%");
        image.getStyle().set("height",image.getHeight());
        image.getStyle().set("width",image.getWidth());
        verticalText.setWidth("30%");
        layout.setAlignItems(FlexComponent.Alignment.START); // Align items to the top

        // Set layout width to fill the available space
        // layout.setVerticalComponentAlignment(FlexComponent.Alignment.START); // Center-align items vertically

        // Add a CSS class for styling
        layout.addClassName("article-style");

        return layout;
    }

    @Transactional
    private Component createTagDisplayComponent(WebPage item) {
        Set<Tag> tags = item.getTags();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        //Add border and rounded corners
        horizontalLayout.setWidth("20%"); // Ensure it takes up only the required space
        horizontalLayout.setHeight("20%");
        horizontalLayout.setPadding(false); // Disable padding to minimize extra space

        for(Tag tag: tags) {
            horizontalLayout.add(tag.createTagComponent());
        }
        return horizontalLayout;
    }

}
