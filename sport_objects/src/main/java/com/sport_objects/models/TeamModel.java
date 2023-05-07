package com.sport_objects.models;

import com.sport_objects.entities.Team;

public class TeamModel {

    private Long id;
    private String name;
    private String createDate;
    private SportTypeModel sportType;

    public static TeamModel toModel(Team entity) {
        TeamModel model = new TeamModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCreateDate(entity.getCreateDate());

        if (entity.getSportType() != null)
            model.setSportType(SportTypeModel.toModel(entity.getSportType()));

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public SportTypeModel getSportType() {
        return sportType;
    }

    public void setSportType(SportTypeModel sportType) {
        this.sportType = sportType;
    }

}
