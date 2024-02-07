package com.gabeWebTest.webTest.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WebPageRepository extends JpaRepository<WebPage, Long> {

    //Finds all webpages ordered by time created
    List<WebPage> findAllByOrderByDates_TimeCreatedDesc();

    //Finds all tags with certain tags
    List<WebPage> findByTagsIn(List<Tag> tags);

    List<WebPage> findByTagsInOrderByDatesTimeCreatedDesc(List<Tag> tags);

    List<WebPage> findByDatesTimeCreatedBetweenAndTagsIn(LocalDate startDate, LocalDate endDate, List<Tag> tags);

}
