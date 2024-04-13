package com.gabeWebTest.webTest.data.visualSource;



import jakarta.persistence.*;

//This entity is used for storing a table of all images/resources used.

@Entity
public class VisualSource {

    public VisualSource() {

    }

    public VisualSource(String imagePath) {
        this.imagePath = imagePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", sequenceName = "your_sequence_name", initialValue = 5)
    @Column(name = "SOURCE_ID")
    private int id;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }
}
