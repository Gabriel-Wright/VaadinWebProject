package com.gabeWebTest.webTest.views.errorRoutes;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;
import jakarta.servlet.http.HttpServletResponse;

@ParentLayout(ErrorView.class)
public class RouteNotFound extends AppLayout
        implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        Div errorMessageDiv = new Div();
        Paragraph errorMessage = new Paragraph("Could not navigate to '"
                + event.getLocation().getPath()
                + "'. This is either because there is an error in the URL entered into the web browser or the page you are looking for has been deleted."
                + " Please return to the main view.");
        // Set the custom font for the error message
        errorMessageDiv.addClassName("article-text");
        errorMessageDiv.add(errorMessage);
        setContent(errorMessageDiv);

        return HttpServletResponse.SC_NOT_FOUND;
    }
}
