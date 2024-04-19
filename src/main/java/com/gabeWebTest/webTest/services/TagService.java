package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import com.gabeWebTest.webTest.data.webPage.tags.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    public List<Tag> findAllTagsOrdered() { return tagRepository.findAllByOrderByTagNameAsc();}

    public Optional<Tag> findByTagName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }
    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
