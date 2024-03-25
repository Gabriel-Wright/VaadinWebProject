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
        contentView.add(getAboutMeBlock());
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
        String intoText = "My name is Gabriel and I'm a " + age+ " year old from the UK with a strong dsire to build and create." +
                "I've had what always felt like a split of interests between STEM and ";
        Paragraph paragraph = new Paragraph(intoText);
        paragraph.addClassName("about-me-text");
        titleAndTextBlock.add(title);
        titleAndTextBlock.add(paragraph);
        return titleAndTextBlock;
    }

    private VerticalLayout getEducationBlock() {
        VerticalLayout verticalLayout = new VerticalLayout();
        H1 title = new H1("Educational Background");
        title.addClassName("about-me-title");
        return verticalLayout;
    }

    private VerticalLayout getCVBlock() {
        VerticalLayout cvBlock = new VerticalLayout();
        cvBlock.addClassName("cv-block");
        cvBlock.getStyle().set("margin","auto");
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
