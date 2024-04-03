package com.gabeWebTest.webTest.views;

import com.gabeWebTest.webTest.data.webPage.Title;
import com.gabeWebTest.webTest.views.dashboard.mainDrawer.MainDrawer;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
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
        contentView.add(getHobbyBlock());
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
        String educationText1 = "I completed my Undergraduate degree at the University of Warwick between September 2018 and July 2021. Initially, I pursued a joint degree in Maths and Philosophy, "+
               "due to my split interest in both subjects and their interconnectedness. Particularly intriguing to me was the exploration of mathematical logic and its implications on metaphysical concepts of truth.";
        String educationText2 = "During my 2nd year I transitioned to a full BSc in Mathematics. This shift stemmed from a realization that Mathematics offered a more tangible avenue for academic pursuit and growth. "+
                "While I valued and was engaged by the philosophy discussion in lectures, I found the structured framework of the Mathematics degree suited me better. "+
                "In the later years of my degree I specialised within Mathematical Analysis and Abstract Algebra.";
        String educationText3 = "Following my undergraduate degree I started on a master's program between October 2021 and September 2022. During the summer of 2021, before the start of my next academic year, I found myself increasingly drawn to the fields of computer science and artificial intelligence. " +
                "Recognizing the growing importance and applications of these disciplines, I decided to pivot towards a more hands-on researched based MSc in Data Analytics. "+
                "Throughout my MSc program, I learnt various data analytic techniques including but not limited to: .";
        Paragraph paragraph1 = new Paragraph();
        Paragraph paragraph2 = new Paragraph();
        Paragraph paragraph3 = new Paragraph();
        Paragraph paragraph4 = new Paragraph();

        paragraph1.add(educationText1);
        paragraph1.addClassName("about-me-text");
        paragraph2.add(educationText2);
        paragraph2.addClassName("about-me-text");
        paragraph3.add(educationText3);
        paragraph3.addClassName("about-me-text");
        titleAndTextBlock.add(title, paragraph1, paragraph2, paragraph3);
        Span bullet1 = new Span("• Data pre-processing and cleaning techniques.");
        Span bullet2 = new Span("• Machine learning algorithms such as regression, classification, clustering and neural networks.");
        Span bullet3 = new Span("• Statistical modelling, including hypothesis testing and utilising probabilistic modelling techniques to predict future outcomes.");
        Span bullet4 = new Span("• Current applications and implementations of Natural Language Processing, such as in Sentiment Analysis, Information Extraction and Recommender Systems.");
        Span bullet5 = new Span("• Algorithmic Game Theory approaches to solving game theoretic problems efficiently.");
        bullet1.addClassName("about-me-text");
        bullet2.addClassName("about-me-text");
        bullet3.addClassName("about-me-text");
        bullet4.addClassName("about-me-text");
        bullet5.addClassName("about-me-text");
        titleAndTextBlock.add(bullet1, bullet2, bullet3, bullet4, bullet5);
        return titleAndTextBlock;
    }

    private HorizontalLayout getHobbyBlock() {
        HorizontalLayout hobbyBlock = new HorizontalLayout();
        hobbyBlock.addClassName("hobby-block");
        hobbyBlock.getStyle().set("margin","auto");
        hobbyBlock.add(getHobbyImage());
        hobbyBlock.add(getHobbyTextBlock());
        return hobbyBlock;
    }

    private Image getHobbyImage() {
        Image hobbyImage = new Image("img/aboutMe/smashPlaying.png","Gabriel playing smash");
        hobbyImage.addClassName("hobby-image");
        return hobbyImage;
    }

    private VerticalLayout getHobbyTextBlock() {
        VerticalLayout titleAndTextBlock = new VerticalLayout();
        H1 title = new H1("Hobbies & Side projects");
        title.addClassName("about-me-title");
        String hobbiesText = "";
        Paragraph paragraph = new Paragraph();
        paragraph.add(hobbiesText);
        paragraph.addClassName("about-me-text");
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
