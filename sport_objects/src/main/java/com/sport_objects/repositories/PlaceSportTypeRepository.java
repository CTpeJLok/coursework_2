package com.sport_objects.repositories;

import com.sport_objects.entities.PlaceSportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceSportTypeRepository extends JpaRepository<PlaceSportType, Long> {
    List<PlaceSportType> findPlaceSportTypesByPlaceId(Long id);
}
