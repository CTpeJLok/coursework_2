package com.sport_objects.repositories;

import com.sport_objects.entities.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventPlaceRepository extends JpaRepository<EventPlace, Long> {
    List<EventPlace> findEventPlacesByEventId(Long id);
}
