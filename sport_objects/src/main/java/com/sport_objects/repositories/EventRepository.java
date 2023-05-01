package com.sport_objects.repositories;

import com.sport_objects.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE CONCAT(LOWER(e.name), ' ', LOWER(e.createDate), ' ', LOWER(e.updateDate), ' ', LOWER(e.cancelDate), ' ', LOWER(e.startDate), ' ', LOWER(e.endDate), ' ', LOWER(e.description)) LIKE %?1%")
    List<Event> searchKeyword(String searchKeyword);

}
