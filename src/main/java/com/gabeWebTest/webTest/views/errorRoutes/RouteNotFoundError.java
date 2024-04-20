package com.gabeWebTest.webTest.views.errorRoutes;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.server.auth.AccessDeniedErrorRouter;
import jakarta.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
//@AccessDeniedErrorRouter
public class RouteNotFoundError extends Component
        implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        getElement().setText("Could not navigate to '"
                + event.getLocation().getPath()
                + "'");
        return HttpServletResponse.SC_NOT_FOUND;
    }
}