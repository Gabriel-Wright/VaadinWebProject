package com.gabeWebTest.webTest.data.webPage.articleFormat;

import jakarta.persistence.*;

@Entity
public class ArticleFormat {

    public ArticleFormat() {
    }

    public ArticleFormat(Long id, String formatName) {
        this.id = id;
        this.formatName = formatName;
    }

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
