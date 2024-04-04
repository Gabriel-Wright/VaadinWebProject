package com.gabeWebTest.webTest.views.whatIsThisSite;

import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("what-is-this-site")
@PageTitle("What is this site?")
public class WhatIsThisSiteView extends AppLayout {

    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;

    private final String springUrl = "https://www.youtube.com/embed/mNsV7-nmCI0?si=YNLaCGS9WDyM2w6K";
    private final String vaadinUrl = "";
    @Autowired
    public WhatIsThisSiteView(NavigationBar navigationbar, MainDrawer mainDrawer) {
//        addClassName("about-me");
        addClassName("dashboard-layout");
        setPrimarySection(AppLayout.Section.DRAWER);
        this.navigationBar = navigationbar;
        this.mainDrawer = mainDrawer;
        setDrawerOpened(true);
        navigationBar.setDrawToggleRight(true);
        DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(navigationbar.createNavBarContent(drawerToggle, "What is this site?"));
        addToDrawer(mainDrawer.createDrawerContent());
        setContent(createContent());
    }

    private VerticalLayout createContent() {
        VerticalLayout content = new VerticalLayout();
        String iframeHtml = "<iframe width=\"560\" height=\"315\" src=\""+springUrl+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";

        // Create an Html component and set the HTML content
        Html html = new Html(iframeHtml);

        // Add the Html component to the layout
        content.add(html);

        return content;
    }
}
