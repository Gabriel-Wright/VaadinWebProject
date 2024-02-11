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

    @Autowired
    public DashboardView(TagFilter tagFilter, NavigationBar navigationBar) {
        this.tagFilter = tagFilter;
        this.navigationBar = navigationBar;
        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(navigationBar.createNavBarContent(toggle));
        addToDrawer(new SideNavItem("Home", "/",
                VaadinIcon.HOME.create()));
        addToDrawer(new SideNavItem("Random article","/",
                VaadinIcon.RANDOM.create()));
        // Create a divider to visually separate the top and bottom sections of the drawer
        Div divider = new Div();
        divider.setHeight("50%"); // Adjust the height as needed
        addToDrawer(divider);
        addToDrawer(tagFilter.createTagFilterDropDown());
//        createTagFilterDropDown();
        Button testButton = new Button("TEST");
        setContent(testButton);
    }

//    private Component createNavBarContent(DrawerToggle drawerToggle) {
//        HorizontalLayout layout = new HorizontalLayout();
//        layout.setId("header");
//        layout.setAlignItems(FlexComponent.Alignment.CENTER);
//        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
//        layout.setWidthFull();
//
//
//        // Add the drawer toggle to the layout
//        layout.add(drawerToggle);
//
////        // Add the view title to the layout
////        viewTitle = new H1("Your Title"); // Replace "Your Title" with the actual title
////        layout.add(viewTitle);
//
//
//        // Add the LinkedIn image and link
//        Image linkedInImage = new Image("img/LI-In-Bug.png", "LinkedIn");
//        linkedInImage.addClassName("responsive-logo");
//        linkedInImage.getElement().getStyle().set("max-width", "100%");
//        linkedInImage.getElement().getStyle().set("height", "auto");
//        Anchor linkedinLinkImage = new Anchor("https://www.linkedin.com/in/gabriel-wright-127b0724b/", linkedInImage);
////        Div linkedinDiv = new Div(linkedinLinkImage);
////        // Adjust margins and padding for the Div
////        linkedinDiv.getElement().getStyle().set("margin", "0");
////        linkedinDiv.getElement().getStyle().set("padding", "0");
//
//
//        layout.add(linkedinLinkImage);
//
//
//        return layout;
//    }

//    private void createTagFilterDropDown() {
//        // Get all tags from TagService
//        List<Tag> tags = tagService.findAllTags();
//
//        MultiSelectComboBox<Tag> filterDropDown = new MultiSelectComboBox<>();
//        filterDropDown.setLabel("Filter by topic");
//        filterDropDown.setItemLabelGenerator(Tag::getTagName);
//        filterDropDown.setItems(tags);
//        filterDropDown.setAutoExpand(MultiSelectComboBox.AutoExpandMode.BOTH);
//        // Add listener to the filter dropdown
//        filterDropDown.addValueChangeListener(event -> {
//            //set selected tags to these
//            Set<Tag> selectedTags = event.getValue();
//            //Filter articles based on selected tags
//            //WebPageService.findByTags(selectedTags);
//            //Update display with filtered articles
//            //updateArticleDisplay(filteredArticles);
//
//        });
//
//
//        // Add the filter dropdown to the bottom of the drawer
//        addToDrawer(new VerticalLayout(filterDropDown));
//    }

}
