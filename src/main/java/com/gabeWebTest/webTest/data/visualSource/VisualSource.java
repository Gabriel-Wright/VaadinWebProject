package com.gabeWebTest.webTest.data.visualSource;



import jakarta.persistence.*;

//This entity is used for storing a table of all images/resources used.

@Entity
public class VisualSource {

    public VisualSource() {

    }

    @Id
    @Column(name = "SOURCE_ID")
    private int id;

    @Column(name = "IMAGE_PATH")
    private String imagePath;
}
