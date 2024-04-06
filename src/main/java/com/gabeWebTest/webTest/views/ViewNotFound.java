package com.gabeWebTest.webTest.views;

import com.gabeWebTest.webTest.views.dashboard.DashboardView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteNotFoundError;
@Route(value = "404", layout = DashboardView.class)
public class ViewNotFound extends RouteNotFoundError {

    public ViewNotFound() {
        System.out.println("Rerouting");
    }
}
