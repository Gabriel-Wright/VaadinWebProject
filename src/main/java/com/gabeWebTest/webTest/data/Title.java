package com.gabeWebTest.webTest.data;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Title extends WebPage {

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }
}
