package com.gabeWebTest.webTest.data.webPage;

import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class WebPage {

    //Default constructor needed for hibernate
    public WebPage() {

    }

    public WebPage(String title, String articleTextPath, String thumbnailPath, String articlePreviewText, Set<Tag> tags) {
        this.title = title;
        this.articleTextPath = articleTextPath;
        this.thumbnailPath = thumbnailPath;
        this.articlePreviewText = articlePreviewText;
        this.tags = tags;
        version = 1;
        setDefaultDates();
    }

    public WebPage(Long id, int version, String title, String articleText, Dates dates, Set<Tag> tags) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.articlePreviewText = articleText;
        this.dates = dates;
        this.tags = tags;
    }

    //This defines how the primary key Id is handled in the database.
    //Basically just increments by 1, starting at Id = 1000
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 0)
    private Long id;

//    @Column(name ="Article_Active")
//    private boolean active;
    //This stores the version of the entry i.e. after updates or changes to DB.
    @Version
    private int version;

    private String title;

    @Column(name="Article_Preview")
    private String articlePreviewText;

    @Column(name="Article_Text_Path")
    private String articleTextPath;

    @Column(name="THUMBNAIL")
    private String thumbnailPath;

    @Embedded
    private Dates dates;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "webpage_tag", // custom join table name
            joinColumns = @JoinColumn(name = "webpage_id"), // column in the join table referencing WebPage
            inverseJoinColumns = @JoinColumn(name = "tag_id") // column in the join table referencing Tag
    )
    private Set<Tag> tags;

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

    public String getArticlePreviewText() {
        return articlePreviewText;
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

    @Override
    public String toString() {
        return "WebPage{" +
                "id=" + id +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", articlePreviewText='" + articlePreviewText + '\'' +
                ", articleTextPath='" + articleTextPath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", dates=" + dates.toString() +
                ", tags=" + tags.toString() +
                '}';
    }

    private void setDefaultDates() {
        LocalDateTime currentTime = LocalDateTime.now();
        dates = new Dates(currentTime, currentTime);
    }
}


