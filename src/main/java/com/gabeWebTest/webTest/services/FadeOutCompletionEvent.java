package com.gabeWebTest.webTest.services;

import org.springframework.context.ApplicationEvent;

public class FadeOutCompletionEvent extends ApplicationEvent {
    public FadeOutCompletionEvent(Object source) {
        super(source);
    }
}
