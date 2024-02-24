package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.services.WebPageService;
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

@Route("")
@PageTitle("Dashboard")
public class DashboardView extends AppLayout {

    private H1 viewTitle;
    private final TagFilter tagFilter;
    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    private final WebPageService webPageService;

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
        VirtualList<WebPage> list = new VirtualList<>();
        list.setItems(webPageService.findAllWebPages());
        list.setRenderer(new WebPageListRenderer(HorizontalLayout::new));
        list.setHeightFull();

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull(); // Set the size to fill the entire available space
        content.add(list);
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        setContent(content);

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

}
