package com.sport_objects.repositories;

import com.sport_objects.entities.PlaceSportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceSportTypeRepository extends JpaRepository<PlaceSportType, Long> {

    List<PlaceSportType> findByPlaceId(Long id);

}
