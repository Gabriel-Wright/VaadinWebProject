package com.gabeWebTest.webTest.views;

import com.gabeWebTest.webTest.data.webPage.Title;
import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gabeWebTest.webTest.utils.AgeCalculator.CALCULATE_AGE;

@Route("about-me")
public class AboutMeView extends AppLayout {

    private final NavigationBar navigationBar;
    private final MainDrawer mainDrawer;
    @Autowired
    public AboutMeView(NavigationBar navigationbar, MainDrawer mainDrawer) {
//        addClassName("about-me");
        addClassName("dashboard-layout");
        setPrimarySection(Section.DRAWER);
        this.navigationBar = navigationbar;
        this.mainDrawer = mainDrawer;
        DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(navigationbar.createNavBarContent(drawerToggle,"About me"));
        addToDrawer(mainDrawer.createDrawerContent());
        VerticalLayout contentView = new VerticalLayout();
        contentView.addClassName("about-me-content");
        contentView.add(getAboutMeBlock());
        contentView.add(getEducationBlock());
        contentView.add(getCVBlock());
        setContent(contentView);
    }

    private Html getPdfViewer() {
        Html pdfViewer = new Html("<embed src='pdf/CVGabrielWrightOct2023.pdf' type='application/pdf' width='50%' height='600px' />");
        pdfViewer.addClassName("pdf-viewer");
        return pdfViewer;
    }
    private Image getProfileImage() {
        Image profileImage = new Image("img/aboutMe/gabrielZoo.jpg", "Gabriel Profile picture");
        profileImage.addClassName("profile-image");
        return profileImage;
    }

    private HorizontalLayout getAboutMeBlock() {
        HorizontalLayout aboutMeBlock = new HorizontalLayout();
        aboutMeBlock.addClassName("about-me-block");
        aboutMeBlock.getStyle().set("margin","auto");
        aboutMeBlock.add(getProfileImage());
        aboutMeBlock.add(getAboutMeTextBlock());
        return aboutMeBlock;
    }

    private VerticalLayout getAboutMeTextBlock() {
        VerticalLayout titleAndTextBlock = new VerticalLayout();
        H1 title = new H1("Gabriel Magnus Wright");
        title.addClassName("about-me-title");
        String age = Integer.toString(CALCULATE_AGE());
        String introText = "My name is Gabriel. I am " + age+ ", from the UK and have a strong desire to build and create." +
                " I've always harboured a passion for both STEM and the arts." +
                " While I'm currently specialising in Software Development for my career,  I am equally committed to nurturing my artistic pursuits with separate projects." +
                " This website serves as a log of my journey, documenting progress across all my interests. For more details on the site's contents, please refer to ";
        Html htmlContent = new Html("<a href=\"/what-is-this-site\">\"What is this site\"</a>");

        Paragraph paragraph = new Paragraph();
        paragraph.add(introText);
        paragraph.add(htmlContent);
        paragraph.addClassName("about-me-text");
        titleAndTextBlock.add(title);
        titleAndTextBlock.add(paragraph);
        return titleAndTextBlock;
    }

    private HorizontalLayout getEducationBlock() {
        HorizontalLayout educationBlock = new HorizontalLayout();
        educationBlock.addClassName("education-block");
        educationBlock.getStyle().set("margin","auto");
        educationBlock.add(getEducationImage());
        educationBlock.add(getEducationTextBlock());
        return educationBlock;
    }

    private Image getEducationImage() {
        Image educationImage = new Image("img/aboutMe/education-picture.jpg", "Gabriel University picture");
        educationImage.addClassName("education-image");

        return educationImage;
    }

    private VerticalLayout getEducationTextBlock() {
        VerticalLayout titleAndTextBlock = new VerticalLayout();
        H1 title = new H1("Educational Background");
        title.addClassName("about-me-title");
        String educationText = "";
        Paragraph paragraph = new Paragraph();
        paragraph.add(educationText);
        titleAndTextBlock.add(title, paragraph);
        return titleAndTextBlock;
    }

    private VerticalLayout getCVBlock() {
        VerticalLayout cvBlock = new VerticalLayout();
        cvBlock.addClassName("cv-block");
//        cvBlock.getStyle().set("margin","auto");
        H1 cvTitle = new H1("CV");
        H2 cvNote = new H2("Attached below is a copy of my current CV, for queries contact me at:");
        H2 cvNote2 = new H2("gabriel.magnus.wright@gmail.com");

        cvTitle.addClassName("cv-title");

        cvNote.addClassName("cv-note1");
        cvNote2.addClassName("cv-note2");

        cvBlock.add(cvTitle);
        cvBlock.add(cvNote);
        cvBlock.add(cvNote2);
        cvBlock.add(getPdfViewer());

        return cvBlock;
    }
}
