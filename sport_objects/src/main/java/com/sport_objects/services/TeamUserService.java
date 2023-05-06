package com.sport_objects.services;

import com.sport_objects.entities.TeamUser;
import com.sport_objects.repositories.TeamUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamUserService {

    @Autowired
    private TeamUserRepository teamUserRepository;

    public TeamUser findById(Long id) {
        return teamUserRepository.findById(id).orElse(new TeamUser());
    }

    public List<TeamUser> findByTeamId(Long id, String searchKeyword) {
        if (searchKeyword == null)
            return teamUserRepository.findByTeamId(id);

        return teamUserRepository.findByTeamIdSearch(id, searchKeyword);
    }

    public void save(TeamUser teamUser) {
        try {
            teamUserRepository.save(teamUser);
        } catch (Exception e) {
            
        }
    }

    public void del(Long id) {
        teamUserRepository.deleteById(id);
    }

    public TeamUser get(Long id) {
        return teamUserRepository.findById(id).orElse(new TeamUser());
    }

    public void truncate() {
        teamUserRepository.deleteAll();
    }


    public boolean isExist(Long id) {
        return teamUserRepository.findById(id).isPresent();
    }

}
