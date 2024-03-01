package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.services.FadeOutCompletionEvent;
import com.gabeWebTest.webTest.services.WebPageService;
import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Route("")
@PageTitle("Dashboard")
public class DashboardView extends AppLayout implements TagFilterListener{

    private H1 viewTitle;
    private final TagFilter tagFilter;
    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    private final WebPageService webPageService;
    private final DashboardUIChangeListener dashboardUIChangeListener;

    //These are needed for displaying suitable articles based on filters, and loading the new articles when necessary.
    private List<WebPage> selectedWebPages;
    private VirtualList<WebPage> list;

    @Autowired
    public DashboardView(TagFilter tagFilter, NavigationBar navigationBar, MainDrawer mainDrawer, WebPageService webPageService, DashboardUIChangeListener dashboardUIChangeListener) {
        this.tagFilter = tagFilter;
        this.navigationBar = navigationBar;
        this.mainDrawer = mainDrawer;
        this.webPageService = webPageService;
        this.dashboardUIChangeListener = dashboardUIChangeListener;

        dashboardUIChangeListener.setDashboardView(this);
        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContent(toggle));
        addToDrawer(mainDrawer.createDrawerContent());
        addToDrawer(createDivider());
        addToDrawer(tagFilter.createTagFilterDropDown());

        //Here is where I would have articleDisplayAdded
        list = new VirtualList<>();
        list.getStyle().set("opacity","1");
        list.getStyle().set("transition","opacity 0.5s ease");
        list.setItems(webPageService.findAllWebPages());
        System.out.println(webPageService.findAllWebPages());
        list.setRenderer(new WebPageListRenderer(HorizontalLayout::new));
        list.setHeightFull();

//        updateArticles();

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


    //Check whether webPages are the same as previously - if  they are then leave it
    //If not the same - fade out previous loaded pages.
    //Set new pages
    //Fade in new pages

    private void updateArticles() {
        Set<Tag> selectedTags = tagFilter.getSelectedTags();
        if (selectedTags.isEmpty()) {
            selectedWebPages = webPageService.findAllWebPages();
        } else {
            selectedWebPages = webPageService.findWebPagesByTags(new ArrayList<>(selectedTags));
        }

        // Set the className of the list to "fade-out"
        //Updates element of the list
        list.getStyle().set("opacity","0");
        list.getStyle().set("transition","opacity ease 0.5s");
//        list.setItems(selectedWebPages);

        UI.getCurrent().getPage().executeJs(
                "function fadeOutElementAndNotifyServer() {\n" +
                        "    // Send request to server\n" +
                        "    fetch('/handleFadeOutCompletion', {\n" +
                        "        method: 'POST',\n" +
                        "        headers: {\n" +
                        "            'Content-Type': 'application/json'\n" +
                        "        }\n" +
                        "    });\n" +
                        "}\n" +
                        "setTimeout(fadeOutElementAndNotifyServer, 500);"
        );

    }

    public void handleFadeOutCompletion() {
        // Ensure that the UI access is done within UI.access()
    }

    @EventListener
    public void handleFadeOutCompletion(FadeOutCompletionEvent event) {
        System.out.println("DashboardView listens");
        //        list.setItems(selectedWebPages);
//        list.getStyle().set("opacity","1");
//        list.getStyle().set("transition","opacity 0.5s ease");
    }

    public void testSound() {
        getUI().ifPresent(ui -> ui.access(() -> {
            list.setItems(selectedWebPages);
            list.getStyle().set("opacity","1");
            list.getStyle().set("transition","opacity 0.5s ease");
        }));
    }
    @Override
    public void onTagFilterChanged(Set<Tag> selectedTags) {
        updateArticles();
    }

}
