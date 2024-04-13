package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.services.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private final WebPageService webPageService;

    public UploadController(WebPageService webPageService) {
        this.webPageService = webPageService;
    }

//    @PostMapping("/article")
//    public ResponseEntity<String> uploadImage(@RequestParam("title") String title,
//                                              @RequestParam(""))
}
