package com.gabeWebTest.webTest.views.dashboard;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.stereotype.Component;

@Component
public class NavigationBar {

    private final String linkedINImagePath = "img/LI-In-Bug.png";
    private final String linkedINLink = "https://www.linkedin.com/in/gabriel-wright-127b0724b/";
    private boolean drawToggleRight = true;

    private Icon toggleIcon;
    public HorizontalLayout createNavBarContent(DrawerToggle drawerToggle) {
        HorizontalLayout layout = new HorizontalLayout();
        setupLayout(layout);
        setupDrawerToggle(drawerToggle);
//        // Add the drawer toggle to the layout
        layout.add(drawerToggle);
        addDrawerLinks(layout);
        return layout;
    }

    private void setupLayout(HorizontalLayout layout) {
        layout.setId("header");
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setWidthFull();
    }

    private void setupDrawerToggle(DrawerToggle drawerToggle) {
        toggleIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        toggleIcon.getStyle().set("color","black");

        // Set the content of the DrawerToggle to the horizontal layout
        drawerToggle.setIcon(toggleIcon);
        drawerToggle.addClassName("drawer-toggle");
        setDrawerToggleStyle(drawerToggle);
        drawerToggle.addClickListener(event -> {
            drawToggleRight = !drawToggleRight;
            setDrawerToggleStyle(drawerToggle);
        });
    }

    private void addDrawerLinks(HorizontalLayout layout) {
        Anchor linkedINAnchor = createLinkedInAnchor();

        layout.add(linkedINAnchor);
    }

    private void setDrawerToggleStyle(DrawerToggle drawerToggle) {
        if (drawToggleRight) {
            toggleIcon.removeClassName("drawer-toggle-left");
            toggleIcon.addClassName("drawer-toggle-right");
        } else {
            toggleIcon.removeClassName("drawer-toggle-right");
            toggleIcon.addClassName("drawer-toggle-left");
        }
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
