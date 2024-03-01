//package com.gabeWebTest.webTest.services;
//
//import com.vaadin.flow.server.VaadinServlet;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//import static com.gabeWebTest.webTest.views.dashboard.DashboardView.handleFadeOutCompletion;
//
//@WebServlet(urlPatterns = "/handleFadeOutCompletion/*", name = "MyUIServlet", asyncSupported = true)
//public class UIServlet extends VaadinServlet {
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Handle message from client-side
//        // Call the Java method to handle fade-out completion
//        handleFadeOutCompletion();
//
//        // Send a response back to the client
//        response.setContentType("text/plain");
//        response.getWriter().write("Fade-out completion handled successfully!");
//    }
//}
