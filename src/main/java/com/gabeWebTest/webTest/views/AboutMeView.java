package com.gabeWebTest.webTest.views;

import com.gabeWebTest.webTest.data.webPage.Title;
import com.gabeWebTest.webTest.views.dashboard.navigationBar.NavigationBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

@Route("about-me")
public class AboutMeView extends AppLayout {

    private final NavigationBar navigationBar;

    @Autowired
    public AboutMeView(NavigationBar navigationbar) {
        addClassName("about-me");
        this.navigationBar = navigationbar;
        addToNavbar(navigationbar.createNavBarContent("About me"));
        VerticalLayout contentView = new VerticalLayout();
        HorizontalLayout aboutMeView = new HorizontalLayout();
        aboutMeView.add(getProfileImage());
        VerticalLayout titleAndTextBlock = new VerticalLayout();
        H1 title = new H1("Gabriel Magnus Wright");
        Paragraph paragraph = new Paragraph("Hello! My name is [Your Name], and I'm passionate about [Your Passion or Interest]. With [Number] years of experience in [Your Field or Industry], I've developed a strong foundation in [Key Skills or Expertise]. My journey in [Your Field] has allowed me to [Achievement or Milestone], which has shaped me into a [Your Positive Attribute] professional.\n" +
                "\n" +
                "I thrive in [Describe Work Environment or Situation], where I can [Action or Goal]. My approach to [Your Field] is driven by [Your Approach or Philosophy], always striving for excellence and innovation. I enjoy [Hobbies or Interests] in my free time, as they allow me to [Benefit or Personal Growth].\n" +
                "\n" +
                "I believe in the power of [Your Belief or Principle], and I'm committed to [Your Commitment or Goal]. Whether it's [Specific Goal or Objective], I'm always eager to [Your Motivation or Drive]. I'm excited about the opportunity to [Your Aspiration or Future Endeavor], and I look forward to [Your Hope or Expectation].\n" +
                "\n" +
                "Thank you for taking the time to learn a little about me. I'm eager to connect and explore how we can [Mutual Benefit or Collaboration].\n" +
                "\n");
        titleAndTextBlock.add(title);
        titleAndTextBlock.add(paragraph);
        aboutMeView.add(titleAndTextBlock);
        Html pdfViewer = new Html("<embed src='pdf/patientGabrielWrightSickNote.pdf' type='application/pdf' width='50%' height='600px' />");
        VerticalLayout cvBlock = new VerticalLayout();
        cvBlock.add(pdfViewer);
        contentView.add(aboutMeView);
        contentView.add(cvBlock);
        setContent(contentView);
    }

    private Image getProfileImage() {
        Image profileImage = new Image("img/aboutMe/gabrielZoo.jpg", "Gabriel Profile picture");
        profileImage.addClassName("profile-image");
        return profileImage;
    }
}
