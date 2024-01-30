package com.gabeWebTest.webTest.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
@PageTitle("Dashboard")
public class Dashboard extends AppLayout {

    private H1 viewTitle;

    public Dashboard() {
        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(createNavBarContent(toggle));
        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

//        Image linkedInImage = new Image("img/LI-In-Bug.png", "LinkedIn");
//        linkedInImage.addClassName("responsive-logo");
//        linkedInImage.getElement().getStyle().set("max-width", "8%");
//        linkedInImage.getElement().getStyle().set("height", "auto");
//
//        Anchor linkedinLinkImage = new Anchor("https://www.linkedin.com/in/gabriel-wright-127b0724b/", linkedInImage);
//        Button testButton = new Button(linkedinLinkImage);
////        testButton.setWidthFull();
//        // Create a div to hold the LinkedIn button and set its alignment to the right
//        Div linkedinDiv = new Div(testButton);
////        linkedinDiv.setClassName("hyperlink-padding");
//        linkedinDiv.getElement().getStyle().set("margin-right", "auto");


        addToDrawer(scroller);

        setPrimarySection(Section.DRAWER);
    }

    private SideNav getSideNav() {
        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("Dashboard", "/",
                VaadinIcon.RANDOM.create()));
        return nav;
    }

    private Component createNavBarContent(DrawerToggle drawerToggle) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setWidthFull();


        // Add the drawer toggle to the layout
        layout.add(drawerToggle);

        // Add the view title to the layout
        viewTitle = new H1("Your Title"); // Replace "Your Title" with the actual title
        layout.add(viewTitle);


        // Add the LinkedIn image and link
        Image linkedInImage = new Image("img/LI-In-Bug.png", "LinkedIn");
        linkedInImage.addClassName("responsive-logo");
        linkedInImage.getElement().getStyle().set("max-width", "100%");
        linkedInImage.getElement().getStyle().set("height", "auto");
        Anchor linkedinLinkImage = new Anchor("https://www.linkedin.com/in/gabriel-wright-127b0724b/", linkedInImage);
//        Div linkedinDiv = new Div(linkedinLinkImage);
//        // Adjust margins and padding for the Div
//        linkedinDiv.getElement().getStyle().set("margin", "0");
//        linkedinDiv.getElement().getStyle().set("padding", "0");


        layout.add(linkedinLinkImage);


        return layout;
    }

}
