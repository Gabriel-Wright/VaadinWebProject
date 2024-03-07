package com.gabeWebTest.webTest.views.dashboard;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//This contains the navigation bar and the drop-down drawer


@Component
public class MainDrawer {

    public com.vaadin.flow.component.Component createDrawerContent() {

        SideNav drawerContent = new SideNav();
//        drawerContent.addClassName("drawer-buttons");
        drawerContent.addItemAsFirst(createSideNavButton("Home", "/", VaadinIcon.HOME));
        drawerContent.addItem(createSideNavButton("Random article","/", VaadinIcon.RANDOM));

//        // Create a divider to visually separate the top and bottom sections of the drawer
//        Div divider = new Div();
//        divider.setHeight("50%"); // Adjust the height as needed
//        drawerContent.add(divider);

        return drawerContent;
    }

    private SideNavItem createSideNavButton(String name, String path, VaadinIcon icon) {
        SideNavItem sideNavItem = new SideNavItem(name, path);
        sideNavItem.setClassName("drawer");
        return sideNavItem;
    }
}
