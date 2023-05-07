package com.sport_objects.models;

import com.sport_objects.entities.Place;

public class PlaceModel {

    private Long id;
    private String name;
    private String location;
    private String description;

    public static PlaceModel toModel(Place entity) {
        PlaceModel model = new PlaceModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setLocation(entity.getLocation());
        model.setDescription(entity.getDescription());
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
