package com.sport_objects.repositories;

import com.sport_objects.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p FROM Position p WHERE LOWER(p.name) LIKE %?1%")
    List<Position> searchKeyword(String searchKeyword);

}
