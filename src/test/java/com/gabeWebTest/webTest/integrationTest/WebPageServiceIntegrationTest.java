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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFindWebPagesByTags() {
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
        Tag tag3 = new Tag(2, "tag3");

        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);

        //Web pages
        Set<Tag> tagSet1 = new HashSet<>();
        tagSet1.add(tag1);
        Dates webPage1Dates = new Dates(LocalDateTime.of(2023, 1, 1, 10, 0, 0),
                LocalDateTime.of(2023, 1, 1, 10, 0, 0));
        WebPage webPage1 = new WebPage(1L,0,"webpage1",new ArticleText(),null,webPage1Dates, tagSet1, articleFormat1);

        Set<Tag> tagSet2 = new HashSet<>();
        tagSet2.add(tag1);
        tagSet2.add(tag2);
        Dates webPage2Dates = new Dates(LocalDateTime.of(2023, 1, 2, 10, 0,0), LocalDateTime.of(2023,1,2,10,0,0));
        WebPage webPage2 = new WebPage(2L, 0, "webpage2", new ArticleText(), null, webPage2Dates, tagSet2, articleFormat2);

        Set<Tag> tagSet3 = new HashSet<>();
        tagSet3.add(tag3);
        Dates webPage3Dates = new Dates(LocalDateTime.of(2023, 1, 3, 10, 0,0), LocalDateTime.of(2023,1,2,10,0,0));
        WebPage webPage3 = new WebPage(3L, 0, "webpage3", new ArticleText(), null, webPage3Dates, tagSet3, articleFormat1);

        webPageRepository.save(webPage1);
        webPageRepository.save(webPage2);
        webPageRepository.save(webPage3);

        List<Tag> tag1List = new ArrayList<>();
        tag1List.add(tag1);
        List<WebPage> webPagesWithTag1 = webPageRepository.findByTagsIn(tag1List);

        assertTrue(webPagesWithTag1.contains(webPage1));
        assertTrue(webPagesWithTag1.contains(webPage2));
        assertEquals(2,webPagesWithTag1.size());

        List<Tag> tag1And3List = new ArrayList<>();
        tag1And3List.add(tag1);
        tag1And3List.add(tag3);
        List<WebPage> webPagesWithTag1And3 = webPageRepository.findByTagsIn(tag1And3List);

        assertTrue(webPagesWithTag1And3.contains(webPage1));
        assertTrue(webPagesWithTag1And3.contains(webPage2));
        assertTrue(webPagesWithTag1And3.contains(webPage3));

        assertEquals(3, webPagesWithTag1And3.size());

        assertFalse(webPagesWithTag1.contains(webPage3));
    }
}
