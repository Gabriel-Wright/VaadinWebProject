package com.gabeWebTest.webTest.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class ArticleText extends WebPage{

    @NotBlank
    @Lob
    private String articleText;

    public String getArticleText() {
        return articleText;
    }
}
