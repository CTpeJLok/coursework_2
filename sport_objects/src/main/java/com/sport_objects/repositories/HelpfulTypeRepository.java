package com.sport_objects.repositories;

import com.sport_objects.entities.HelpfulType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HelpfulTypeRepository extends JpaRepository<HelpfulType, Long> {

    @Query("SELECT h FROM HelpfulType h WHERE LOWER(h.name) LIKE %?1%")
    List<HelpfulType> searchKeyword(String searchKeyword);

}
