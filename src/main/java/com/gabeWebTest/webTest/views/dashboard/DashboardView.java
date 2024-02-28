package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.services.WebPageService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Route("")
@PageTitle("Dashboard")
public class DashboardView extends AppLayout implements TagFilterListener{

    private H1 viewTitle;
    private final TagFilter tagFilter;
    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    private final WebPageService webPageService;

    //These are needed for displaying suitable articles based on filters, and loading the new articles when necessary.
    private VirtualList<WebPage> list;

    @Autowired
    public DashboardView(TagFilter tagFilter, NavigationBar navigationBar, MainDrawer mainDrawer, WebPageService webPageService) {
        this.tagFilter = tagFilter;
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;
        this.webPageService = webPageService;

        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContent(toggle));
        addToDrawer(mainDrawer.createDrawerContent());
        addToDrawer(createDivider());
        addToDrawer(tagFilter.createTagFilterDropDown());

        //Here is where I would have articleDisplayAdded
        list = new VirtualList<>();
//        list.setItems(webPageService.findAllWebPages());
        list.setRenderer(new WebPageListRenderer(HorizontalLayout::new));
        list.setHeightFull();

        updateArticles();

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull(); // Set the size to fill the entire available space
        content.add(list);
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        setContent(content);

        tagFilter.setTagFilterListener(this);

//        // Create a FlexLayout to contain the content
//        FlexLayout flexLayout = new FlexLayout();
//        flexLayout.setSizeFull();
//        flexLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
//        flexLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
//        flexLayout.add(content);
//
//        // Set the content of the AppLayout
//        setContent(flexLayout);

    }


    private Div createDivider() {
        Div divider = new Div();
        divider.setHeight("50%"); // Adjust the height as needed

        return divider;
    }

    private void updateArticles() {
        List<WebPage> webPages;
        Set<Tag> selectedTags = tagFilter.getSelectedTags();
        if (selectedTags.isEmpty()) {
            webPages = webPageService.findAllWebPages();
        } else {
            webPages = webPageService.findWebPagesByTags(new ArrayList<>(selectedTags));
        }

        // Set the className of the list to "fade-out"
        list.getElement().executeJs("this.style.transition = 'opacity 0.5s'; this.style.opacity = '0';");

        // Set a timeout to update the items and change the className back to "fade-in" after 500 milliseconds
        UI.getCurrent().getPage().executeJs("setTimeout(function() { "
                + "var list = document.querySelector('vaadin-virtual-list'); "
                + "list.style.opacity = '1'; "
                + "list.style.transition = 'opacity 0.5s';"
                + "}, 500);");

        // After a delay, set the new items
        UI.getCurrent().getPage().executeJs("setTimeout(function() { "
                + "var list = document.querySelector('vaadin-virtual-list'); "
                + "list.render();"
                + "}, 500);");

        list.setItems(webPages);
    }


    @Override
    public void onTagFilterChanged(Set<Tag> selectedTags) {
        updateArticles();
    }
}
