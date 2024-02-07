package com.gabeWebTest.webTest.integrationTest;
import com.gabeWebTest.webTest.data.*;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional //ensures each test method runs within a transaction, and changes are rolled back after each test method
@ActiveProfiles("test")
public class WebPageServiceIntegrationTest {

    @Autowired //inject the WebPageService bean using @Autowired to test its method
    private WebPageRepository webPageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArticleFormatRepository articleFormatRepository;

    @Test
    public void testFindAllWebPagesOrderByCreatedDateDesc() {
        //Clear existing data
        webPageRepository.deleteAll();
        articleFormatRepository.deleteAll();
        tagRepository.deleteAll();


        //Article formats
        ArticleFormat articleFormat1 = new ArticleFormat(0L, "test1");
        ArticleFormat articleFormat2 = new ArticleFormat(1L, "test2");
        articleFormatRepository.save(articleFormat1);
        articleFormatRepository.save(articleFormat2);

        //Tag formats
        Tag tag1 = new Tag(0, "tag1");
        Tag tag2 = new Tag(1, "tag2");
        tagRepository.save(tag1);
        tagRepository.save(tag2);

        //Web pages
        Dates webPage1Dates = new Dates(LocalDateTime.of(2023, 1, 1, 10, 0, 0),
                LocalDateTime.of(2023, 1, 1, 10, 0, 0));
        WebPage webPage1 = new WebPage(1L,0,"webpage1",new ArticleText(),null,webPage1Dates, null, articleFormat1);

        Dates webPage2Dates = new Dates(LocalDateTime.of(2023, 1, 2, 10, 0,0), LocalDateTime.of(2023,1,2,10,0,0));
        WebPage webPage2 = new WebPage(2L, 0, "webpage2", new ArticleText(), null, webPage2Dates, null, articleFormat2);
        webPageRepository.save(webPage1);
        webPageRepository.save(webPage2);
        List<WebPage> webPages = webPageRepository.findAllByOrderByDates_TimeCreatedDesc();

        assertEquals(2, webPages.size(),"Size of webpages list");

        LocalDateTime latestDate = webPages.get(0).getDates().getTimeCreated();
        LocalDateTime earlierDate = webPages.get(1).getDates().getTimeCreated();
        Assertions.assertTrue(latestDate.isAfter(earlierDate),"Sorting by date test");
    }

    @Test
    public void testFindNumberOfTags() {
        //Delete all existing tags
        tagRepository.deleteAll();

        //Tag formats
        Tag tag1 = new Tag(0, "tag1");
        Tag tag2 = new Tag(1, "tag2");
        tagRepository.save(tag1);
        tagRepository.save(tag2);

        List<Tag> tags = tagRepository.findAll();
        //Need to sort this so that right databases are checked
        assertEquals(2, tags.size());
    }

}
