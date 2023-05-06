package com.sport_objects.repositories;

import com.sport_objects.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p WHERE CONCAT(LOWER(p.name), ' ', LOWER(p.description)) LIKE %?1%")
    List<Place> searchKeyword(String searchKeyword);

}
