package com.gabeWebTest.webTest.views.security.upload;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route("upload")
@PageTitle("Upload Files")
@PermitAll
public class UploadView extends AppLayout {

    UploadView() {
        setContent(new H1("WOOOO WE MADE IT"));
    }
}
