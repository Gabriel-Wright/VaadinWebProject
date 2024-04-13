package com.gabeWebTest.webTest.data.webPage.tags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    // Method to find a tag by its name
    public Tag findByTagName(String tagName);

    // Method to save a tag
    public Tag save(Tag tag);

}
