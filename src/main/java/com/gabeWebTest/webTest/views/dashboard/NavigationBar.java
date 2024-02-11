package com.gabeWebTest.webTest.views.dashboard;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.stereotype.Component;

@Component
public class NavigationBar {

    private final String linkedINImagePath = "img/LI-In-Bug.png";
    private final String linkedINLink = "https://www.linkedin.com/in/gabriel-wright-127b0724b/";

    public HorizontalLayout createNavBarContent(DrawerToggle drawerToggle) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setWidthFull();

        // Add the drawer toggle to the layout
        layout.add(drawerToggle);

        Anchor linkedINAnchor = createLinkedInAnchor();

        layout.add(linkedINAnchor);

        return layout;
    }

    private Anchor createLinkedInAnchor() {
        // Add the LinkedIn image and link
        Image linkedInImage = new Image(linkedINImagePath, "LinkedIn");
        linkedInImage.addClassName("responsive-logo");
        linkedInImage.getElement().getStyle().set("max-width", "100%");
        linkedInImage.getElement().getStyle().set("height", "auto");
        return new Anchor(this.linkedINLink, linkedInImage);
    }

}
