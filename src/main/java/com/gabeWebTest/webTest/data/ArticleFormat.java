package com.gabeWebTest.webTest.data;

import jakarta.persistence.*;

@Entity
public class ArticleFormat {

    //Id of article format
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    @Column(name = "article_format_id") // Specify the column name here
    private int id;

    @Column(name = "name")
    private String formatName;

    public int getId() {
        return id;
    }

    public String getFormatName() {
        return formatName;
    }
}
