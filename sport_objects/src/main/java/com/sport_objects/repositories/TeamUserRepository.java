package com.sport_objects.repositories;

import com.sport_objects.entities.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {

    @Query("SELECT t FROM TeamUser t WHERE CONCAT(LOWER(t.entryDate), ' ', LOWER(t.exitDate), ' ', t.user.id) LIKE %?1%")
    List<TeamUser> searchKeyword(String searchKeyword);


    @Query("SELECT t FROM TeamUser t WHERE t.team.id = ?1 AND CONCAT(LOWER(t.entryDate), ' ', LOWER(t.exitDate), ' ', t.user.id) LIKE %?2%")
    List<TeamUser> findByTeamIdSearch(Long id, String searchKeyword);

    List<TeamUser> findByTeamId(Long id);

}
