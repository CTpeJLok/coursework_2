package com.sport_objects.models;

import com.sport_objects.entities.PlaceSportType;

public class PlaceSportTypeModel {

    private Long id;
    private String size;
    private SportTypeModel sportType;
    private PlaceModel place;

    public static PlaceSportTypeModel toModel(PlaceSportType entity) {
        PlaceSportTypeModel model = new PlaceSportTypeModel();
        model.setId(entity.getId());
        model.setSize(entity.getSize());

        if (entity.getSportType() != null)
            model.setSportType(SportTypeModel.toModel(entity.getSportType()));

        if (entity.getPlace() != null)
            model.setPlace(PlaceModel.toModel(entity.getPlace()));

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public SportTypeModel getSportType() {
        return sportType;
    }

    public void setSportType(SportTypeModel sportType) {
        this.sportType = sportType;
    }

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.place = place;
    }

}
