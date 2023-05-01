package com.sport_objects.services;

import com.sport_objects.entities.TeamUser;
import com.sport_objects.repositories.TeamUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamUserService {

    @Autowired
    private TeamUserRepository rep;

    public List<TeamUser> findAll() {
        return rep.findAll();
    }

    public List<TeamUser> findByTeamID(Long id) {
        return rep.findTeamUsersByTeamId(id);
    }

    public void save(TeamUser teamUser) {
        rep.save(teamUser);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public TeamUser get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
