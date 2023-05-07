package com.sport_objects.models;

import com.sport_objects.entities.TeamUser;

public class TeamUserModel {

    private Long id;
    private String entryDate;
    private String exitDate;
    private TeamModel team;
    private UserModel user;

    public static TeamUserModel toModel(TeamUser entity) {
        TeamUserModel model = new TeamUserModel();
        model.setId(entity.getId());
        model.setEntryDate(model.getEntryDate());
        model.setExitDate(model.getExitDate());

        if (entity.getTeam() != null)
            model.setTeam(TeamModel.toModel(entity.getTeam()));

        if (entity.getUser() != null)
            model.setUser(UserModel.toModel(entity.getUser()));

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
