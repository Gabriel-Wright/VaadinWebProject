package com.gabeWebTest.webTest.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

//    //Filter by webpages by tags in between a certain date range by date created descending
//    List<WebPage> findByDatesTimeCreatedBetweenAndTagsInOrderByDatesTimeCreatedDesc(LocalDateTime startDate, LocalDateTime endDate, List<Tag> tags);


}
