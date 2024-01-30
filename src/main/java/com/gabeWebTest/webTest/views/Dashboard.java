package com.gabeWebTest.webTest.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
@PageTitle("Dashboard")
public class Dashboard extends AppLayout {


    public Dashboard() {
        DrawerToggle toggle = new DrawerToggle();

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        Image linkedInImage = new Image("img/LI-In-Bug.png", "LinkedIn");
        Anchor linkedinLinkImage = new Anchor("https://www.linkedin.com/in/gabriel-wright-127b0724b/", linkedInImage);

        Button button = new Button("LinkedIn");
        button.setIcon(linkedinLinkImage);
        button.addThemeVariants(ButtonVariant.LUMO_ICON);
        // Create a div to hold the LinkedIn button and set its alignment to the right
        Div linkedinButtonDiv = new Div(button);
        linkedinButtonDiv.getElement().getStyle().set("margin-left", "auto");


        addToDrawer(scroller);
        addToNavbar(toggle);
//        addToNavbar(button);
        addToNavbar(linkedinButtonDiv);

        setPrimarySection(Section.DRAWER);
    }

    private SideNav getSideNav() {
        SideNav nav = new SideNav();
        Image linkedInImage = new Image("img/LI-In-Bug.png", "LinkedIn");

        nav.addItem(new SideNavItem("Dashboard","/",
                VaadinIcon.RANDOM.create()),
                new SideNavItem("LinkedIn","/",
                        linkedInImage));
        return nav;
    }
}
