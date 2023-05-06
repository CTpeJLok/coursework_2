package com.sport_objects.repositories;

import com.sport_objects.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t WHERE CONCAT(LOWER(t.name), ' ', LOWER(t.createDate)) LIKE %?1%")
    List<Team> searchKeyword(String searchKeyword);

}
