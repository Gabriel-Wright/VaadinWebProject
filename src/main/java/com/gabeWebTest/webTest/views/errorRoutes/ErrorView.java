package com.gabeWebTest.webTest.views.errorRoutes;

import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import org.springframework.beans.factory.annotation.Autowired;

public class ErrorView extends AppLayout {

    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;

    @Autowired
    public ErrorView(NavigationBar navigationBar, MainDrawer mainDrawer) {
        addClassName("dashboard-layout");
        setPrimarySection(AppLayout.Section.DRAWER);
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;
        setDrawerOpened(true);
        navigationBar.setDrawToggleRight(true);
        DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContent(drawerToggle, "Page Not Found?"));
        addToDrawer(mainDrawer.createDrawerContent());
    }

}
