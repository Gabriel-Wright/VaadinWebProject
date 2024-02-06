package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.WebPageRepository;
import org.springframework.stereotype.Service;

@Service
public class WebPageService {

    private final WebPageRepository webPageRepository;

    public WebPageService(WebPageRepository webPageRepository) {
        this.webPageRepository = webPageRepository;
    }

}
