package com.sport_objects.services;

import com.sport_objects.entities.Team;
import com.sport_objects.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository rep;

    public List<Team> findAll() {
        return rep.findAll();
    }

    public List<Team> findAll(String searchKeyword) {
        return rep.searchKeyword(searchKeyword);
    }

    public void save(Team team) {
        rep.save(team);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Team get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
