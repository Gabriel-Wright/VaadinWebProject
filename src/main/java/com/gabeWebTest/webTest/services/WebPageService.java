package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebPageService {

    private final WebPageRepository webPageRepository;

    public WebPageService(WebPageRepository webPageRepository) {
        this.webPageRepository = webPageRepository;
    }

    public List<WebPage> findAllWebPages() {
        return webPageRepository.findAll();
    }

    public List<WebPage> findWebPagesByTags(List<Tag> tags) {
        return webPageRepository.findByTagsIn(tags);
    }

    public List<WebPage> findWebPagesByTagsOrderedByDateDesc(List<Tag> tags) {
        return webPageRepository.findByTagsInOrderByDatesTimeCreatedDesc(tags);
    }


    //Will not receieve a webPage if id is null
    public Optional<WebPage> findWebPageById(long id) {
        return webPageRepository.findById(id);
    }
}
