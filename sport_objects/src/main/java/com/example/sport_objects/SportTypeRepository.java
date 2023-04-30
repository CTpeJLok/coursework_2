package com.example.sport_objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SportTypeRepository extends JpaRepository<SportType, Long> {
    @Query("SELECT s FROM SportType s WHERE LOWER(s.name) LIKE %?1%")
    List<SportType> searchKeyword(String searchKeyword);
}
