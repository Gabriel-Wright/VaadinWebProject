package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Title;
import com.gabeWebTest.webTest.data.WebPage;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
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
        layout.add(createLinkedInAnchor());
        return layout;
    }

    public HorizontalLayout createNavBarContentArticle(DrawerToggle drawerToggle, WebPage webPage) {
        HorizontalLayout layout = new HorizontalLayout();
        setupLayout(layout);
        setupDrawerToggle(drawerToggle);
        layout.add(drawerToggle);
        layout.add(createTitle(webPage));
        layout.add(createLinkedInAnchor());

        return layout;
    }

    private H2 createTitle(WebPage webPage) {
        H2 title = new H2(webPage.getTitle());
        title.addClassName("article-title");

        return title;
    }

    private void setupLayout(HorizontalLayout layout) {
        layout.setId("header");
        layout.setPadding(false);
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

    private Anchor createDrawerLinks () {
        Anchor linkedINAnchor = createLinkedInAnchor();

        return linkedINAnchor;
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
        Anchor linkedInAnchor = new Anchor(linkedINLink, linkedInImage);
        // Add a class to the anchor for styling (if needed)
        linkedInAnchor.addClassName("linkedin-anchor");
        // Set the anchor's alignment to the right
        linkedInAnchor.getElement().getStyle().set("margin-left", "auto");
        linkedInImage.setWidth("auto");
        return linkedInAnchor;
    }

}
