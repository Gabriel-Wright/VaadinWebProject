package com.gabeWebTest.webTest.data;

import com.vaadin.flow.component.icon.VaadinIcon;
import jakarta.persistence.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    public Tag() {
    }

    public Tag(int id, String tagName, Color color) {
        this.id = id;
        this.tagName = tagName;
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private int id;

    @Column(name = "TAGNAME")
    private String tagName;

    @Column(name = "COLOR_HEX")
    private String colorHex;

    @Transient //Not persistent in the database
    private Color color;

    @Column(name = "ICON")
    @Enumerated(EnumType.STRING)
    private VaadinIcon icon;

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

    public Color getColor() {
        if (color == null && colorHex != null) {
            color = convertHexToColor(colorHex);
        }
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.colorHex = convertColorToHex(color);
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
        this.color = convertHexToColor(colorHex);
    }

    private String convertColorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private Color convertHexToColor(String hex) {
        return Color.decode(hex);
    }

    public VaadinIcon getIcon() {
        return icon;
    }

    public void setIcon(VaadinIcon icon) {
        this.icon = icon;
    }
}
