package com.gabeWebTest.webTest.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private int id;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<WebPage> abstractEntities = new HashSet<>();

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
