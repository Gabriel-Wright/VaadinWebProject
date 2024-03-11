package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.services.WebPageService;
import com.gabeWebTest.webTest.views.dashboard.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.NavigationBar;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("article")
public class ArticleView extends AppLayout implements HasUrlParameter<Long> {

    @Autowired
    private final WebPageService webPageService;
    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    private WebPage webPage;
    public ArticleView(WebPageService webPageService, NavigationBar navigationBar, MainDrawer mainDrawer) {
        addClassName("dashboard-layout");
        this.webPageService = webPageService;
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;

        addToDrawer(mainDrawer.createDrawerContent());
        setPrimarySection(Section.DRAWER);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        webPage = webPageService.findWebPageById(aLong).orElse(null);
        loadNavBar();
        loadPage();
    }

    private void loadNavBar() {
        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContentArticle(toggle, webPage));
    }

    private void loadPage() {
        VerticalLayout articleLayout = new VerticalLayout();
        H1 titleOfArticle = new H1(webPage.getTitle());
        titleOfArticle.setSizeFull();
        articleLayout.add(loadThumbnail());
        articleLayout.add(titleOfArticle);
        setContent(articleLayout);
    }

    private Image loadThumbnail() {
        Image image;
        if(webPage.getThumbnailPath()==null) {
            image = new Image("img/LI-In-Bug.png", "ArticleImage");
        } else {
            image = new Image(webPage.getThumbnailPath(),webPage.getTitle());
        }

        image.addClassName("article-thumbnail");

        return image;
    }

}
