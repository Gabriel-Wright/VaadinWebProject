package com.gabeWebTest.webTest.data.webPage.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    // Method to find a tag by its name
    public Optional<Tag> findByTagName(String tagName);

    // Method to find all tags ordered alphabetically
    public List<Tag> findAllByOrderByTagNameAsc();

    // Method to save a tag
    public Tag save(Tag tag);

}
