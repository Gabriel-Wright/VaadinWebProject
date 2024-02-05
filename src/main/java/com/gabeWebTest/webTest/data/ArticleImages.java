package com.gabeWebTest.webTest.data;

import jakarta.persistence.Embeddable;

import java.util.Set;

@Embeddable
public class ArticleImages {

    private Set<String> imagePaths;

    public Set<String> getImagePaths() {
        return imagePaths;
    }
}
