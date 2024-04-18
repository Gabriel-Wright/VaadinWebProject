package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.gabeWebTest.webTest.services.WebPageService;
import com.gabeWebTest.webTest.utils.ImageLoader;
import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;


@Route("article")
@AnonymousAllowed
public class ArticleView extends AppLayout implements HasUrlParameter<Long>, HasDynamicTitle {

    private static final Logger logger = LoggerFactory.getLogger(ArticleView.class);

    @Autowired
    private final WebPageService webPageService;
    @Autowired
    private final VisualSourceService visualSourceService;

    private ImageLoader imageLoader;

    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    private String title;
    private WebPage webPage;
    public ArticleView(WebPageService webPageService, VisualSourceService visualSourceService, NavigationBar navigationBar, MainDrawer mainDrawer) {
        addClassName("dashboard-layout");
        this.webPageService = webPageService;
        this.visualSourceService = visualSourceService;
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;
        imageLoader = new ImageLoader();
        imageLoader.setVisualSourceService(visualSourceService);
        setDrawerOpened(true);
        navigationBar.setDrawToggleRight(true);

        addToDrawer(mainDrawer.createDrawerContent());
        setPrimarySection(Section.DRAWER);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        webPage = webPageService.findWebPageById(aLong).orElse(null);
        loadNavBar();
        loadPage();
        if(webPage!=null) {
            setPageTitle();
        } else {
            title = "Article";
        }
    }

    private void setPageTitle() {
        title = webPage.getTitle();
    }
    private void loadNavBar() {
        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContentArticle(toggle, webPage));
    }

    private void loadPage() {
        VerticalLayout articleLayout = new VerticalLayout();
//        H1 titleOfArticle = new H1(webPage.getTitle());
//        titleOfArticle.setSizeFull();
//        articleLayout.add(loadThumbnail());
        articleLayout.add(new Image("middleResources/img/monika.png","img/github-mark.png"));
        articleLayout.add(loadTitleOfArticle());
        ArticleTextBlockGenerator articleTextBlockGenerator = new ArticleTextBlockGenerator(webPage, visualSourceService, imageLoader);
        articleLayout.add(articleTextBlockGenerator.loadArticleTextBlock());
        setContent(articleLayout);
    }

    private VerticalLayout loadTitleOfArticle() {
        VerticalLayout titleBlock = new VerticalLayout();
        H1 titleOfArticle = new H1(webPage.getTitle());
        titleOfArticle.addClassName("article-text-block-title");
        titleBlock.add(titleOfArticle);
        titleBlock.add(loadWebPageDate());
        titleBlock.addClassName("title-block");
        return titleBlock;
    }

    private Span loadWebPageDate() {
        LocalDate webPageCreationDate = webPage.getDates().getTimeCreated().toLocalDate();
        LocalTime webPageCreationTime = webPage.getDates().getTimeCreated().toLocalTime();
        LocalDate webPageLastUpdateDate = webPage.getDates().getTimeLastUpdated().toLocalDate();
        LocalTime webPageLastUpdateTime = webPage.getDates().getTimeLastUpdated().toLocalTime();

        Span articleDate = new Span(webPageCreationDate.toString());
        articleDate.addClassName("article-date");

        String dateTimeCreated = "Article created:" +webPageCreationDate.toString() +" " +webPageCreationTime.toString();
        String dateLastUpdated = "Article last Updated:" + webPageLastUpdateDate.toString() + " " + webPageLastUpdateTime.toString();
        Tooltip dateTip = Tooltip.forComponent(articleDate).
                withText(dateTimeCreated +"\n"+dateLastUpdated).
                withPosition(Tooltip.TooltipPosition.BOTTOM);
        return articleDate;
    }

    @Override
    public String getPageTitle() {
        return title;
    }
}
