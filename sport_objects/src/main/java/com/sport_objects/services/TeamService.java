package com.sport_objects.services;

import com.sport_objects.entities.Team;
import com.sport_objects.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll(String searchKeyword) {
        if (searchKeyword == null)
            return teamRepository.findAll();

        return teamRepository.searchKeyword(searchKeyword);
    }

    public void save(Team team) {
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            
        }
    }

    public void del(Long id) {
        teamRepository.deleteById(id);
    }

    public Team get(Long id) {
        return teamRepository.findById(id).orElse(new Team());
    }

    public void truncate() {
        teamRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return teamRepository.findById(id).isPresent();
    }

}
