package com.gabeWebTest.webTest.data.webPage.tags;

import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import jakarta.persistence.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    private final Color defaultColor = Color.RED;

    public Tag() {
    }


    //Id will be set automatically upon being saved in repo
    public Tag(String tagName) {
        this.tagName = tagName;
        this.color = defaultColor;
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

    public String getTransparentColorRGB(int alpha) {
        Color color = getColor();
        return String.format("rgba(%d, %d, %d, %.2f",color.getRed(), color.getGreen(), color.getBlue(), alpha / 255.0);
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

    public Component createTagComponent() {
        Span tagSpan = new Span(tagName);

        int alphaTransparency = 50;
        tagSpan.getStyle().set("background-color",getTransparentColorRGB(alphaTransparency));
        tagSpan.getStyle().set("color", getColorHex());
        tagSpan.setClassName("tag-badge");

        return tagSpan;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "defaultColor=" + defaultColor +
                ", id=" + id +
                ", tagName='" + tagName + '\'' +
                ", colorHex='" + colorHex + '\'' +
                ", color=" + color +
                ", icon=" + icon +
                ", webPages=" + webPages +
                '}';
    }
}
