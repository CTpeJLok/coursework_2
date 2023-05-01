package com.sport_objects.repositories;

import com.sport_objects.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT t FROM Team t WHERE LOWER(t.name) LIKE %?1%")
    List<Place> searchKeyword(String searchKeyword);

}
