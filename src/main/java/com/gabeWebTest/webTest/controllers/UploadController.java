package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import com.gabeWebTest.webTest.data.webPage.tags.TagRepository;
import com.gabeWebTest.webTest.services.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private final WebPageService webPageService;
    private final TagRepository tagRepository;

    public UploadController(WebPageService webPageService, TagRepository tagRepository) {
        this.webPageService = webPageService;
        this.tagRepository = tagRepository;
    }

//    @PostMapping("/article")
//    public ResponseEntity<String> uploadImage(@RequestParam("title") String title,
//                                              @RequestParam("article_text_path") String articleTextPath,
//                                              @RequestParam("thumbnail_path") String thumbnail,
//                                              @RequestParam("article_preview_text") String previewText,
//                                              @RequestParam("tags") Set<String> tags) {
//        WebPage()
//
//    }

    private Set<Tag> resolveTags(Set<String> tagStringSet) {
        // Convert tag names to Tag entities
        Set<Tag> convertedTags = new HashSet<>();
        for (String tagName : tagStringSet) {
            Tag tag = tagRepository.findByTagName(tagName);
            if (tag == null) {
                // Create a new Tag entity if it doesn't exist
                tag = new Tag(tagName);
                tag = tagRepository.save(tag);
            }
            convertedTags.add(tag);
        }
        return convertedTags;
    }
}
