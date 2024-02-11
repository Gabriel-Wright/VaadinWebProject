package com.gabeWebTest.webTest.views.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PageTitle("Dashboard")
public class DashboardView extends AppLayout {

    private H1 viewTitle;
    private final TagFilter tagFilter;
    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;

    @Autowired
    public DashboardView(TagFilter tagFilter, NavigationBar navigationBar, MainDrawer mainDrawer) {
        this.tagFilter = tagFilter;
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;

        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContent(toggle));
        addToDrawer(mainDrawer.createDrawerContent());
        addToDrawer(createDivider());
        addToDrawer(tagFilter.createTagFilterDropDown());

        //Here is where I would have articleDisplayAdded
        Button testButton = new Button("TEST");
        setContent(testButton);
    }

    private Div createDivider() {
        Div divider = new Div();
        divider.setHeight("50%"); // Adjust the height as needed

        return divider;
    }

}
