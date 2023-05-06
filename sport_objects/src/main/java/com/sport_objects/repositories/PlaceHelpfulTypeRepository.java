package com.sport_objects.repositories;

import com.sport_objects.entities.PlaceHelpfulType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceHelpfulTypeRepository extends JpaRepository<PlaceHelpfulType, Long> {

    List<PlaceHelpfulType> findByPlaceId(Long id);

}
