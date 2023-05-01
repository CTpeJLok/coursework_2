package com.sport_objects.repositories;

import com.sport_objects.entities.PlaceHelpfulType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceHelpfulTypeRepository extends JpaRepository<PlaceHelpfulType, Long> {
    List<PlaceHelpfulType> findPlaceHelpfulTypesByPlaceId(Long id);
}
