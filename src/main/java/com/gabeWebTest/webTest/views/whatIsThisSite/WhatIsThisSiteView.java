package com.gabeWebTest.webTest.views.whatIsThisSite;

import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

@Route("what-is-this-site")
@PageTitle("What is this site?")
@AnonymousAllowed
public class WhatIsThisSiteView extends AppLayout {

    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;

    private final String springUrl = "https://www.youtube.com/embed/mNsV7-nmCI0?si=YNLaCGS9WDyM2w6K";
    private final String vaadinUrl = "https://www.youtube.com/embed/3WqwKLz43JM?si=bLhKS0Xcj7ORtOBB";
    private final String tutorialUrl = "https://www.youtube.com/embed/bxy2JgqqKDU?si=iZMElPnxuHwDtAoH";
    @Autowired
    public WhatIsThisSiteView(NavigationBar navigationbar, MainDrawer mainDrawer) {
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

    private Div createContent() {
        Div content = new Div();
        content.add(siteIntroExplanation());
        content.add(siteWhy());
        content.add(createTechStackExplanation());
        content.addClassName("wit-content");
        content.getStyle().set("margin","auto");
//        content.add(new Image(testStreamResource(), "AltText"));
        return content;
    }

//    private StreamResource testStreamResource() {
//        StreamResource streamResource = new StreamResource("LI-In-Bug.png", () -> {
//            try {
//                InputStream inputStream = getClass().getResourceAsStream("/static/img/LI-In-Bug.png");
//                return inputStream;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        });
//
//        return streamResource;
//    }

    private Div siteIntroExplanation() {
        Div div = new Div();
        div.addClassName("tech-stack");
        div.add(createTitle("What is this site?"));
        String introText = "This website serves as a hub for me to showcase personal projects I am working on and discuss separate areas of interest."+
                " I intend to continually update this site with new entries/articles covering a range of topics, such as Software development, gaming and film.";
        div.add(createParagraph(introText));
        return div;
    }

    private Div siteWhy() {
        Div div = new Div();
        div.addClassName("tech-stack");
        div.add(createTitle("Why did I make this site?"));
        String whyText = "I created this website with two main objectives in mind. Firstly, this site serves as a platform for me to show and express my projects and thoughts. "+
                "Secondly the creation of this site has been a project in itself. I used this site's development as an opportunity to hone my skills in full-stack development.";
        div.add(createParagraph(whyText));
        return div;
    }
    private H1 createTitle(String titleText) {
        H1 title = new H1(titleText);
        title.addClassName("wit-title");
        return title;
    }

    private Html createYoutubeEmbed(String url) {
        String iframeHtml = "<iframe width=\"560\" height=\"315\" src=\""+url+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        // Create an Html component and set the HTML content
        Html html = new Html(iframeHtml);
        html.addClassName("youtube-embed");
        html.getStyle().set("margin","auto");

        return html;
    }

    private Paragraph createParagraph(String text) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.addClassName("wit-text");
        return paragraph;
    }

    private Div createTechStackExplanation() {
        Div techStack = new Div();

        Div backEnd = createBackEndSection();
        techStack.add(backEnd);

        Div frontEnd = createFrontEndSection();
        techStack.add(frontEnd);

        Div acknowledgements = createAcknowledgementsSection();
        techStack.add(acknowledgements);

        Div policyDiv = createPolicySection();
        techStack.add(policyDiv);

        return techStack;
    }

    private Div createBackEndSection() {
        Div backEnd = new Div();
        backEnd.addClassName("tech-stack");
        backEnd.add(createTitle("Back end"));
        String backEndParagraph = "The backend of this site is powered by Java and Springboot, a popular java framework. Springboot simplifies development by providing several key features. " +
                "Springboot's auto-configuration capability automatically configures various components based on project dependencies, reducing the need for manual setup." +
                " I leveraged Springboot's auto-configuration to support specific tasks relevant to web development, data access and data security." +
                " Utilisation of Spring JPA was used to streamline access to my database layer, with MongoDB serving as my database of choice.";
        backEnd.add(createParagraph(backEndParagraph));
        backEnd.add(createYoutubeEmbed(springUrl));
        return backEnd;
    }

    private Div createFrontEndSection() {
        Div frontEnd = new Div();
        frontEnd.addClassName("tech-stack");
        frontEnd.add(createTitle("Front end"));
        String frontEndParagraph = "The frontend of this site is created using Vaadin, a Java framework that allows developers to build web applications while minimising the need to write in HTML, CSS and Javascript. "+
                "Vaadin lets developers write UI code entirely in Java. Vaadin also integrated with Springboot, this creates a cohesive environment that makes interactions between the front and back end simple.";
        frontEnd.add(createParagraph(frontEndParagraph));
        frontEnd.add(createYoutubeEmbed(vaadinUrl));
        return frontEnd;
    }

    private Div createAcknowledgementsSection() {
        Div acknowledgements = new Div();
        acknowledgements.addClassName("tech-stack");
        acknowledgements.add(createTitle("Acknowledgements/Resources used"));
        String resourcesUsedText1 = "In the development of this site, I mostly referred to the ";
        Html vaadinDocumentation = new Html("<a href=\"https://vaadin.com/docs/latest/tutorial/overview\">Vaadin V24 documentation</a>");
        String resourcesUsedText2 = ". I got started with this project by reviewing the tutorial below.";
        Paragraph frontEndParagraphs = new Paragraph();
        frontEndParagraphs.add(resourcesUsedText1);
        frontEndParagraphs.add(vaadinDocumentation);
        frontEndParagraphs.add(resourcesUsedText2);
        frontEndParagraphs.addClassName("wit-text");
        acknowledgements.add(frontEndParagraphs);
        acknowledgements.add(createYoutubeEmbed(tutorialUrl));
        return acknowledgements;
    }

    private Div createPolicySection() {
        Div policyDiv = new Div();
        policyDiv.addClassName("tech-stack");
        policyDiv.add(createTitle("My policy on the site's content"));
        String policy = "All written entries on this website are created by Gabriel Magnus Wright. "+
                "Readers are welcome to use this content for their own purposes, provided that proper reference to my original work is provided. " +
                "In instances where external content is used within entries or articles on this website, proper credit will be given to the original creators and sources. " +
                "If there are any concerns or objections regarding the usage or references of external content on this site, please contact me at: ";
        Html email = new Html("<a href=\"mailto:gabriel.magnus.wright@gmail.com\">gabriel.magnus.wright@gmail.com</a>");
        Paragraph policyParagraph = new Paragraph();
        policyParagraph.add(policy);
        policyParagraph.add(email);
        policyParagraph.addClassName("wit-text");
        policyDiv.add(policyParagraph);
        return policyDiv;
    }
}
