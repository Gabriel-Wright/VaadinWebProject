package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.gabeWebTest.webTest.services.WebPageService;
import com.gabeWebTest.webTest.utils.ImageLoader;
import com.gabeWebTest.webTest.views.dashboard.content.UIChangeEventListener;
import com.gabeWebTest.webTest.views.dashboard.content.WebPageListRenderer;
import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.gabeWebTest.webTest.views.dashboard.tagDisplay.TagFilter;
import com.gabeWebTest.webTest.views.dashboard.tagDisplay.TagFilterListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Route("")
@PageTitle("Welcome | Projects & Thoughts")
@JavaScript("js/custom.js")
@AnonymousAllowed
public class DashboardView extends AppLayout implements TagFilterListener {

    private H1 viewTitle;
    private final TagFilter tagFilter;
    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    private final WebPageService webPageService;
    private final UIChangeEventListener dashboardUIChangeListener;
    @Autowired
    private final VisualSourceService visualSourceService;
    private ImageLoader imageLoader;

    //These are needed for displaying suitable articles based on filters, and loading the new articles when necessary.
    private List<WebPage> selectedWebPages;
    private VirtualList<WebPage> list;

    @Autowired
    public DashboardView(TagFilter tagFilter, NavigationBar navigationBar, MainDrawer mainDrawer, WebPageService webPageService, UIChangeEventListener dashboardUIChangeListener,
                         VisualSourceService visualSourceService) {
        addClassName("dashboard-layout");

        this.tagFilter = tagFilter;
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;
        this.webPageService = webPageService;
        this.dashboardUIChangeListener = dashboardUIChangeListener;
        this.visualSourceService = visualSourceService;
        this.imageLoader = new ImageLoader();
        imageLoader.setVisualSourceService(visualSourceService);
        setDrawerOpened(true);
        navigationBar.setDrawToggleRight(true);
        dashboardUIChangeListener.setDashboardView(this);
        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContent(toggle));
        addToDrawer(mainDrawer.createDrawerContent());
        addToDrawer(createDivider());
        addToDrawer(tagFilter.createTagFilterDropDown());

        initialiseVirtualListView();
        initialiseContentView();

        setPrimarySection(Section.DRAWER);

        tagFilter.setTagFilterListener(this);
    }


    private Div createDivider() {
        Div divider = new Div();
        divider.setHeight("20%"); // Adjust the height as needed

        return divider;
    }

    private void initialiseContentView() {
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull(); // Set the size to fill the entire available space
        content.add(list);
//        DrawerToggle toggle = new DrawerToggle();
//        Icon icon = new Icon(VaadinIcon.ARROW_DOWN);
//        toggle.setIcon(icon);
////        toggle.getStyle().set("position","fixed");
//        content.add(toggle);
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        setContent(content);
    }

    private void initialiseVirtualListView() {
        //Here is where I would have articleDisplayAdded
        list = new VirtualList<>();
//        list.getStyle().set("opacity","1");
//        list.getStyle().set("transition","opacity 0.5s ease");
        selectedWebPages = webPageService.findAllActiveWebPagesOrderedByDateDesc();
        list.setItems(selectedWebPages);
        list.setRenderer(new WebPageListRenderer(Div::new, imageLoader));
        list.setHeightFull();
        list.addClassName("articlelist-style");
    }

    //Check whether webPages are the same as previously - if  they are then leave it
    //If not the same - fade out previous loaded pages.
    //Call javascript function which has a callback once articles are faded out - to fade in new articles

    private void updateArticles() {
        Set<Tag> selectedTags = tagFilter.getSelectedTags();
        List<WebPage> newWebPageSelection;
        if (selectedTags.isEmpty()) {
            newWebPageSelection = webPageService.findAllActiveWebPagesOrderedByDateDesc();
        } else {
            newWebPageSelection = webPageService.findAllActiveWebPagesOrderedByDateDescWithTags(new ArrayList<>(selectedTags));
        }

        if(!updateNecessary(newWebPageSelection)) {
            return;
        }

        selectedWebPages = newWebPageSelection;
        fadeOutVirtualList();
    }

    //If newWebPageSelection is not the same as selectedWebPages then it is necessary if not then it isnt
    private boolean updateNecessary(List<WebPage> newWebPageSelection) {
        return !newWebPageSelection.equals(selectedWebPages);
    }


    private void fadeOutVirtualList() {
        //Fade out old list
        list.getStyle().set("opacity","0");
        list.getStyle().set("transition","opacity ease 0.5s");

        UI.getCurrent().getPage().executeJs("fadeOutElementAndNotifyServer();");
    }

    //Lock UI thread to then set new webpages and set opacity style
    public void fadeInNewList() {
        getUI().ifPresent(ui -> ui.access(() -> {
            list.setItems(selectedWebPages);
            list.getStyle().set("opacity","1");
            list.getStyle().set("transition","opacity 0.5s ease");
        }));
    }

    //If TagFilters changed then updateArticles
    @Override
    public void onTagFilterChanged(Set<Tag> selectedTags) {
        updateArticles();
    }

}
