//package com.gabeWebTest.webTest.views.errorRoutes;
//
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.Tag;
//import com.vaadin.flow.router.AccessDeniedException;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.ErrorParameter;
//import com.vaadin.flow.router.HasErrorParameter;
//import com.vaadin.flow.server.HttpStatusCode;
//import jakarta.annotation.security.PermitAll;
//
//@Tag(Tag.DIV)
//@PermitAll
//public class CustomAccessDeniedError extends Component
//        implements HasErrorParameter<AccessDeniedException> {
//    @Override
//    public int setErrorParameter(BeforeEnterEvent event,
//                                 ErrorParameter<AccessDeniedException> parameter) {
//        getElement().setText("Access denied.");
//        return HttpStatusCode.UNAUTHORIZED.getCode();
//    }
//}