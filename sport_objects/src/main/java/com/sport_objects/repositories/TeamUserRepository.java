package com.sport_objects.repositories;

import com.sport_objects.entities.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
    List<TeamUser> findTeamUsersByTeamId(Long id);
}
