package com.gabeWebTest.webTest.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    public Tag() {
    }

    public Tag(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private int id;

    @Column(name = "TAGNAME")
    private String tagName;

    @ManyToMany
    @JoinTable(
            name = "webpage_tag", // same join table as in WebPage entity
            joinColumns = @JoinColumn(name = "tag_id"), // column in the join table referencing Tag
            inverseJoinColumns = @JoinColumn(name = "webpage_id") // column in the join table referencing WebPage
    )
    private Set<WebPage> webPages = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public Set<WebPage> getWebPages() {
        return webPages;
    }
}
