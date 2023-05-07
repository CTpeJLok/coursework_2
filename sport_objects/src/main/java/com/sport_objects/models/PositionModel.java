package com.sport_objects.models;

import com.sport_objects.entities.Position;

public class PositionModel {

    private Long id;
    private String name;

    public static PositionModel toModel(Position entity) {
        PositionModel model = new PositionModel();
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
