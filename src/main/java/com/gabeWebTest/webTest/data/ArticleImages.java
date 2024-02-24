package com.gabeWebTest.webTest.data;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.List;
import java.util.Set;

@Embeddable
public class ArticleImages {

    @ElementCollection
    private List<String> imagePaths;

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
