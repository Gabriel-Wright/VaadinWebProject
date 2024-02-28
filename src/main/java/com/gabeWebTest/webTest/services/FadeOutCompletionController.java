package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.views.dashboard.DashboardView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FadeOutCompletionController {

    @PostMapping("/handleFadeOutCompletion")
    @ResponseBody
    public String handleFadeOutCompletion() {
        // Call your Java method here
        DashboardView.handleFadeOutCompletion();

        // Return a response (optional)
        return "Fade-out completion handled successfully!";
    }
}
