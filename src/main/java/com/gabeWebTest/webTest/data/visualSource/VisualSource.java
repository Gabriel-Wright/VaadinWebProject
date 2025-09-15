package com.gabeWebTest.webTest.data.visualSource;



import jakarta.persistence.*;

import static com.gabeWebTest.webTest.utils.FileNameParser.EXTRACT_FILENAME;

//This entity is used for storing a table of all images/resources used.

@Entity
public class VisualSource {

    public VisualSource() {

    }

    public VisualSource(String imagePath) {
        this.imagePath = imagePath;
        this.fileName = EXTRACT_FILENAME(imagePath);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visualsource_seq")
    @SequenceGenerator(name = "visualsource_seq", sequenceName = "visualsource_seq", initialValue = 5)
    @Column(name = "SOURCE_ID")
    private int id;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "FILE_NAME")
    private String fileName;

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getFileName() {
        return fileName;
    }
}
