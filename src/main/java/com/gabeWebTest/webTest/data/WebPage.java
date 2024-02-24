package com.gabeWebTest.webTest.data;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class WebPage {

    //Default constructor needed for hibernate
    public WebPage() {

    }

    public WebPage(Long id, int version, String title, ArticleText articleText, ArticleImages articleImages, Dates dates, Set<Tag> tags, ArticleFormat articleFormat) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.articleText = articleText;
        this.articleImages = articleImages;
        this.dates = dates;
        this.tags = tags;
        this.articleFormat = articleFormat;
    }

    //This defines how the primary key Id is handled in the database.
    //Basically just increments by 1, starting at Id = 1000
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private Long id;

    //This stores the version of the entry i.e. after updates or changes to DB.
    @Version
    private int version;

    private String title;

    @Embedded
    private ArticleText articleText;

    @Column(name="THUMBNAIL")
    private String thumbnailPath;

    @Embedded
    private ArticleImages articleImages;

    @Embedded
    private Dates dates;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "webpage_tag", // custom join table name
            joinColumns = @JoinColumn(name = "webpage_id"), // column in the join table referencing WebPage
            inverseJoinColumns = @JoinColumn(name = "tag_id") // column in the join table referencing Tag
    )
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "article_format_id") // This column will be created in the WebPage table
    private ArticleFormat articleFormat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public ArticleText getArticleText() {
        return articleText;
    }

    public ArticleImages getArticleImages() {
        return articleImages;
    }

    public String getTitle() {
        return title;
    }

    public Dates getDates() {
        return dates;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WebPage)) {
            return false; // null or other class
        }
        WebPage other = (WebPage) obj;

        if (getId() != null) {
            return getId().equals(other.getId());
        }
        return super.equals(other);
    }
}


