package com.gabeWebTest.webTest.data.webPage;

import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebPageRepository extends JpaRepository<WebPage, Long> {

    //Finds all webpages ordered by time created descending
    List<WebPage> findAllByOrderByDates_TimeCreatedDesc();

    //Find all webpages ordered by time created ascending
    List<WebPage> findAllByOrderByDates_TimeCreatedAsc();

    //Finds all tags with certain tags
    List<WebPage> findByTagsIn(List<Tag> tags);

    //Filters by tags in order of date created descending
    List<WebPage> findByTagsInOrderByDatesTimeCreatedDesc(List<Tag> tags);

    //Filters by tags in order of date created ascending
    List<WebPage> findByTagsInOrderByDatesTimeCreatedAsc(List<Tag> tags);

    // Find a webpage by its title
    Optional<WebPage> findByTitle(String title);

    List<WebPage> findByActiveTrueOrderByDatesTimeCreatedDesc();

    List<WebPage> findByActiveTrueAndTagsInOrderByDatesTimeCreatedDesc(List<Tag> tags);


}
