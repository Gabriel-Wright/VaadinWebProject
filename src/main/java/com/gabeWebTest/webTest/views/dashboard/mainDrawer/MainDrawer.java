package com.gabeWebTest.webTest.views.dashboard.mainDrawer;

import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.views.article.ArticleView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

//This contains the navigation bar and the drop-down drawer


@Component
public class MainDrawer {

    private final List<WebPage> webPages;

    @Autowired
    public MainDrawer(List<WebPage> webPages) {
        this.webPages = webPages;
    }

    public com.vaadin.flow.component.Component createDrawerContent() {

        SideNav drawerContent = new SideNav();
//        drawerContent.addClassName("drawer-buttons");
        drawerContent.addItemAsFirst(createSideNavButton("Main view", "/"));
        drawerContent.addItem(createSideNavButton("What is this site?", "/what-is-this-site"));
        drawerContent.addItem(createSideNavButton("About me", "/about-me"));
//        // Create a divider to visually separate the top and bottom sections of the drawer
//        drawerContent.addItem(createRandomArticleButton());
        return drawerContent;
    }

    private SideNavItem createRandomArticleButton() {
        SideNavItem randomArticle = new SideNavItem("Random Article");
        randomArticle.setClassName("drawer-Content");
        long randomId = getRandomId();
        randomArticle.setPath("/article/"+Long.toString(randomId));
        return randomArticle;
    }

    private long getRandomId() {
        if (!webPages.isEmpty()) {
            int randomIndex = new Random().nextInt(webPages.size());
            WebPage randomPage = webPages.get(randomIndex);
            return randomPage.getId();
        }
        return -1000;
    }

    private SideNavItem createSideNavButton(String name, String path) {
        SideNavItem sideNavItem = new SideNavItem(name, path);
        sideNavItem.setClassName("drawer-Content");
        return sideNavItem;
    }
}
