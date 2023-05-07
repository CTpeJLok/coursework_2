package com.sport_objects.models;

import com.sport_objects.entities.SportType;

public class SportTypeModel {

    private Long id;
    private String name;

    public static SportTypeModel toModel(SportType entity) {
        SportTypeModel model = new SportTypeModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
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

}
