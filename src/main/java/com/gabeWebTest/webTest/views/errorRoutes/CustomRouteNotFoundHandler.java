//package com.gabeWebTest.webTest.views.errorRoutes;
//
//import com.vaadin.flow.component.Tag;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.ErrorParameter;
//import com.vaadin.flow.router.NotFoundException;
//import com.vaadin.flow.router.RouteNotFoundError;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Tag("custom-route-not-found-handler")
//public class CustomRouteNotFoundHandler extends RouteNotFoundError {
//
//    @Override
//    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
//        // Optionally, you can display a custom message
//        getElement().setText("The requested resource was not found or you are not authorized to access it.");
//
//        // Redirect the user to the root path
//        event.rerouteTo("");
//
//        return HttpServletResponse.SC_NOT_FOUND;
//    }
//}