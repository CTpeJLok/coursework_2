package com.sport_objects.repositories;

import com.sport_objects.entities.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long> {

    @Query("SELECT e FROM EventPlace e WHERE CONCAT(LOWER(e.event.name), ' ', " +
            "LOWER(e.placeSportType.sportType.name), ' ', LOWER(e.eventDate), ' ', LOWER(e.duration)) LIKE %?1%")
    List<EventPlace> searchKeyword(String searchKeyword);

    List<EventPlace> findByEventId(Long id);

}
