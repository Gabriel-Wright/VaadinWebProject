package com.gabeWebTest.webTest.integrationTest;
import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.data.TagRepository;
import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.data.WebPageRepository;
import com.gabeWebTest.webTest.services.WebPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional //ensures each test method runs within a transaction, and changes are rolled back after each test method
@Sql("/testData.sql") //load test data from a sql file
@ActiveProfiles("test")
public class WebPageServiceIntegrationTest {

    @Autowired //inject the WebPageService bean using @Autowired to test its method
    private WebPageRepository webPageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void testFindAllWebPagesOrderByCreatedDateDesc() {
        List<WebPage> webPages = webPageRepository.findAllByOrderByDates_TimeCreatedDesc();

        assertEquals(3, webPages.size());

        LocalDateTime latestTime = webPages.get(0).getDates().getTimeCreated();
        System.out.println(latestTime);
        LocalDateTime secondTime = webPages.get(1).getDates().getTimeCreated();
        System.out.println(secondTime);
        LocalDateTime earliestTime = webPages.get(2).getDates().getTimeCreated();
        System.out.println(earliestTime);
        assertTrue(latestTime.isAfter(secondTime));
        System.out.println(latestTime.isAfter(secondTime));
        assertTrue(secondTime.isAfter(earliestTime));
        System.out.println(secondTime.isAfter(earliestTime));
    }

    @Test
    public void testFindNumberOfTags() {
        List<Tag> tags = tagRepository.findAll();

        assertEquals(3, tags.size());
    }

}
