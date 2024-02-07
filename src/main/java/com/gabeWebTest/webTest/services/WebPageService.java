package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.ArticleFormatRepository;
import com.gabeWebTest.webTest.data.TagRepository;
import com.gabeWebTest.webTest.data.WebPageRepository;
import org.springframework.stereotype.Service;

@Service
public class WebPageService {

    private final WebPageRepository webPageRepository;
    private final TagRepository tagRepository;
    private final ArticleFormatRepository articleFormatRepository;

    public WebPageService(WebPageRepository webPageRepository, TagRepository tagRepository, ArticleFormatRepository articleFormatRepository) {
        this.webPageRepository = webPageRepository;
        this.tagRepository = tagRepository;
        this.articleFormatRepository = articleFormatRepository;
    }

}
