package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
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
        H2 componentTitle = new H2(item.getTitle());
        componentTitle.addClassName("adaptive-title-font");
//        componentTitle.setWidthFull();

        Paragraph paragraph = new Paragraph("This is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThisThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThisThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThisThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please workThis is preview text dear god please work");
        paragraph.addClassName("article-preview-text");
//        paragraph.setHeight("3em");
//        paragraph.setWidthFull();
        HorizontalLayout tagComponent = createTagDisplayComponent(item);
        tagComponent.setAlignItems(FlexComponent.Alignment.START);
        tagComponent.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        verticalText.add(componentTitle, paragraph, tagComponent);
        verticalText.setPadding(false);
        verticalText.setSpacing(false);
        verticalText.setSizeFull();
//        componentTitle.setHeight("30%");
//        paragraph.setHeight("10%");
//        verticalText.addClassName("article-style");
        //Publication date and tags
        Text publicationText = new Text("Published on" + item.getDates().getTimeCreated());
        // Create a vertical layout to contain the components
        HorizontalLayout layout = new HorizontalLayout(verticalText, image);
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setWidthFull();

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layout.setAlignItems(FlexComponent.Alignment.START);
        verticalText.setWidth("30%");
//        verticalText.setWidthFull();
        layout.expand(verticalText);
//        layout.setAlignItems(FlexComponent.Alignment.START); // Align items to the top
//
//        // Set layout width to fill the available space
//        // layout.setVerticalComponentAlignment(FlexComponent.Alignment.START); // Center-align items vertically

        // Add a CSS class for styling
        layout.addClassName("article-style");

        return layout;
    }

    @Transactional
    private HorizontalLayout createTagDisplayComponent(WebPage item) {
        Set<Tag> tags = item.getTags();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.getStyle().set("flex-wrap","wrap");
        horizontalLayout.setPadding(false); // Disable padding to minimize extra space

        for(Tag tag: tags) {
            horizontalLayout.add(tag.createTagComponent());
            horizontalLayout.add(tag.createTagComponent());
            horizontalLayout.add(tag.createTagComponent());
        }
        return horizontalLayout;
    }

}
