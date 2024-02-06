package com.gabeWebTest.webTest.data;

import jakarta.persistence.*;

@Entity
public class ArticleFormat {

    //Id of article format
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_format_id") // Specify the column name here
    private Long id;

    @Column(name = "name")
    private String formatName;

    public Long getId() {
        return id;
    }

    public String getFormatName() {
        return formatName;
    }
}
