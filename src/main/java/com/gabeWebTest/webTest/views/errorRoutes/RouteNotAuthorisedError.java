package com.gabeWebTest.webTest.views.errorRoutes;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.HttpStatusCode;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
@AnonymousAllowed
public class RouteNotAuthorisedError extends RouteAccessDeniedError {
    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent,
                                 ErrorParameter<AccessDeniedException> errorParameter) {
        getElement().setText("Could not navigate to '"
                + beforeEnterEvent.getLocation().getPath()
                + "'");
        return HttpStatusCode.UNAUTHORIZED.getCode();
    }
}
