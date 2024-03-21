package com.gabeWebTest.webTest.views.dashboard.navigationBar;

import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.stereotype.Component;

@Component
public class NavigationBar {

    private final String linkedINImagePath = "img/LI-In-Bug.png";
    private final String linkedINLink = "https://www.linkedin.com/in/gabriel-wright-127b0724b/";

    private final String gitHubImagePath = "img/github-mark.png";
    private final String gitHubLink = "https://www.github.com/Gabriel-Wright";

    private final String youtubeImagePath = "img/youtube_social_icon_red.png";
    private final String youtubeLink = "https://www.youtube.com/channel/UCe7dUfsqsBkSxSgRizG9XoQ";

    private boolean drawToggleRight = true;

    private Icon toggleIcon;
    public HorizontalLayout createNavBarContent(String title) {
        HorizontalLayout layout = new HorizontalLayout();
        setupLayout(layout);
//        setupDrawerToggle(drawerToggle);
//        layout.add(drawerToggle);
        layout.add(createTitle(title));
        layout.add(createDrawerLinks());
        return layout;
    }

    public HorizontalLayout createNavBarContent(DrawerToggle drawerToggle) {
        HorizontalLayout layout = new HorizontalLayout();
        setupLayout(layout);
        setupDrawerToggle(drawerToggle);
//        // Add the drawer toggle to the layout
        layout.add(drawerToggle);
        layout.add(createTitle("Projects & Thoughts"));
        layout.add(createDrawerLinks());
        return layout;
    }

    public HorizontalLayout createNavBarContentArticle(DrawerToggle drawerToggle, WebPage webPage) {
        HorizontalLayout layout = new HorizontalLayout();
        setupLayout(layout);
        setupDrawerToggle(drawerToggle);
        layout.add(drawerToggle);
        layout.add(createTitle(webPage.getTitle()));
        layout.add(createDrawerLinks());

        return layout;
    }

    private H2 createTitle(String titleString) {
        H2 title = new H2(titleString);
        title.addClassName("article-title");

        return title;
    }

    private void setupLayout(HorizontalLayout layout) {
        layout.setId("header");
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setWidthFull();
    }

    private void setupDrawerToggle(DrawerToggle drawerToggle) {
        toggleIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        toggleIcon.getStyle().set("color","black");

        // Set the content of the DrawerToggle to the horizontal layout
        drawerToggle.setIcon(toggleIcon);
        drawerToggle.addClassName("drawer-toggle");
        setDrawerToggleStyle(drawerToggle);
        drawerToggle.addClickListener(event -> {
            drawToggleRight = !drawToggleRight;
            setDrawerToggleStyle(drawerToggle);
        });
    }

    private HorizontalLayout createDrawerLinks() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Anchor linkedINAnchor = createLinkedInAnchor();
        Anchor gitHubAnchor = createGitHubAnchor();
        Anchor youtubeAnchor = createYoutubeAnchor();
        horizontalLayout.add(youtubeAnchor, linkedINAnchor, gitHubAnchor);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        return horizontalLayout;
    }

    private void setDrawerToggleStyle(DrawerToggle drawerToggle) {
        if (drawToggleRight) {
            toggleIcon.removeClassName("drawer-toggle-left");
            toggleIcon.addClassName("drawer-toggle-right");
        } else {
            toggleIcon.removeClassName("drawer-toggle-right");
            toggleIcon.addClassName("drawer-toggle-left");
        }
    }

    private Anchor createGitHubAnchor() {
        Image gitHubImage = new Image(gitHubImagePath, "Github");
        gitHubImage.addClassName("github-anchor");
        Anchor gitHubAnchor = new Anchor(gitHubLink, gitHubImage);
        gitHubAnchor.setTarget("_blank");
        return gitHubAnchor;
    }

    private Anchor createLinkedInAnchor() {
        // Add the LinkedIn image and link
        Image linkedInImage = new Image(linkedINImagePath, "LinkedIn");
        linkedInImage.addClassName("linkedin-anchor");

//        linkedInImage.addClassName("responsive-logo");
//        linkedInImage.getElement().getStyle().set("max-width", "100%");
//        linkedInImage.getElement().getStyle().set("height", "auto");
        Anchor linkedInAnchor = new Anchor(linkedINLink, linkedInImage);
        linkedInAnchor.setTarget("_blank");
        // Add a class to the anchor for styling (if needed)
//        linkedInAnchor.addClassName("linkedin-anchor");
        // Set the anchor's alignment to the right
//        linkedInAnchor.getElement().getStyle().set("margin-left", "auto");
//        linkedInImage.setWidth("auto");
        return linkedInAnchor;
    }

    private Anchor createYoutubeAnchor() {
        Image youtubeImage = new Image(youtubeImagePath, "Youtube");
        youtubeImage.addClassName("youtube-anchor");

        Anchor youtubeAnchor = new Anchor(youtubeLink, youtubeImage);
        youtubeAnchor.setTarget("_blank");
        return youtubeAnchor;
    }

}
