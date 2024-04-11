//package com.gabeWebTest.webTest.views.security;
//
//import com.vaadin.flow.component.login.LoginForm;
//import com.vaadin.flow.component.login.LoginI18n;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.BeforeEnterObserver;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Route("login")
//@PageTitle("Login")
//@AnonymousAllowed
//public class LoginView extends VerticalLayout implements BeforeEnterObserver {
//
//    private LoginI18n loginI18n;
//    private LoginI18n.Form loginI8nForm;
//    private LoginForm login;
//    private static final Logger logger = LoggerFactory.getLogger(LoginView.class);
//
//
//    public LoginView() {
//        setSizeFull();
//        setAlignItems(Alignment.CENTER);
//        setJustifyContentMode(JustifyContentMode.CENTER);
//
//        loginI18n = LoginI18n.createDefault();
//        loginI8nForm = loginI18n.getForm();
//        loginI8nForm.setSubmit("(●'◡'●)");
//        loginI8nForm.setTitle("");
//        loginI8nForm.setUsername("(⌐■_■)");
//        loginI8nForm.setPassword("¯\\_(ツ)_/¯");
//        loginI8nForm.setForgotPassword("..");
//
//        login = new LoginForm();
//        login.setI18n(loginI18n);
//        login.setAction("login");
//        add(login);
//    }
//
//    @Override
//    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        // inform the user about an authentication error
//        if(beforeEnterEvent.getLocation()
//                .getQueryParameters()
//                .getParameters()
//                .containsKey("error")) {
//            // Log the failed login attempt
//            logger.warn("Failed login attempt for user: {}", beforeEnterEvent.getLocation().getQueryParameters().getParameters().get("username"));
//
//            login.setError(true);
//        }
//    }
//
//}
