package com.gabeWebTest.webTest.data.webPage;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class ArticlePreviewText {

    @NotBlank
    @Lob
    private String articlePreview;

    public String getArticleTextAsString() {
        return articlePreview;
    }
}
