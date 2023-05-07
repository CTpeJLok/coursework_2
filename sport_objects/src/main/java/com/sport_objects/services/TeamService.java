package com.sport_objects.services;

import com.sport_objects.entities.Team;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.TeamModel;
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

    public void save(Team team) throws CreateException {
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            throw new CreateException();
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

    public List<TeamModel> getAll() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(TeamModel::toModel).toList();
    }

    public TeamModel getOne(Long id) throws NotFoundException {
        Team team = teamRepository.findById(id).get();

        if (team == null)
            throw new NotFoundException();

        return TeamModel.toModel(team);
    }

}
