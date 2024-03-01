package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.views.dashboard.DashboardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FadeOutCompletionController {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public FadeOutCompletionController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    //This is used to call out a FadeOutCompletionEvent - needed for allowing the articles to fade in after initially fading out
    @PostMapping("/handleFadeOutCompletion")
    @ResponseBody
    public String handleFadeOutCompletion() {
        // Fire the event
        eventPublisher.publishEvent(new FadeOutCompletionEvent(this));

        return "Fade-out completion handled successfully!";
    }
}
