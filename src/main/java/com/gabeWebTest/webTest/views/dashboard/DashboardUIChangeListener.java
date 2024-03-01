package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.services.FadeOutCompletionEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DashboardUIChangeListener {

    private DashboardView dashboardView;

    public void setDashboardView(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
    }

    @EventListener
    public void handleFadeOutCompletion(FadeOutCompletionEvent event) {
        dashboardView.testSound();
    }

}
