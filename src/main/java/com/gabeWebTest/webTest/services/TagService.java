package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }
}
