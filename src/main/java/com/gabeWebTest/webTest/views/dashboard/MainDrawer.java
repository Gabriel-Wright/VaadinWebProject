package com.gabeWebTest.webTest.views.dashboard;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//This contains the navigation bar and the drop-down drawer


@Component
public class MainDrawer extends DrawerToggle{

    public com.vaadin.flow.component.Component createDrawerContent() {
        Div drawerContent = new Div();
        drawerContent.add(new SideNavItem("Home", "/", VaadinIcon.HOME.create()));
        drawerContent.add(new SideNavItem("Random article", "/", VaadinIcon.RANDOM.create()));

//        // Create a divider to visually separate the top and bottom sections of the drawer
//        Div divider = new Div();
//        divider.setHeight("50%"); // Adjust the height as needed
//        drawerContent.add(divider);

        return drawerContent;
    }
}
