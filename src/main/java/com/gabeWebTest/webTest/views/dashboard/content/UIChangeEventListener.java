package com.gabeWebTest.webTest.views.dashboard.content;

import com.gabeWebTest.webTest.services.FadeOutCompletionEvent;
import com.gabeWebTest.webTest.views.dashboard.DashboardView;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UIChangeEventListener {

    private DashboardView dashboardView;

    public void setDashboardView(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
    }

    @EventListener
    public void handleFadeOutCompletion(FadeOutCompletionEvent event) {
        dashboardView.fadeInNewList();
    }

}
