package com.gabeWebTest.webTest.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class ArticleText {

    @NotBlank
    @Lob
    private String articleText;

    public String getArticleTextAsString() {
        return articleText;
    }
}
