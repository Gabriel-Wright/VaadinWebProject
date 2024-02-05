package com.gabeWebTest.webTest.data;

import jakarta.persistence.Embeddable;

import java.util.Set;

@Embeddable
public class ArticleImages extends WebPage{

    private Set<String> imagePaths;
}
