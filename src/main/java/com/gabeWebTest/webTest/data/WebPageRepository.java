package com.gabeWebTest.webTest.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebPageRepository extends JpaRepository<WebPage, Long> {

    //Finds all webpages ordered by time created
    List<WebPage> findAllByOrderByDates_TimeCreatedDesc();

}
