package com.sport_objects.models;

import com.sport_objects.entities.PlaceHelpfulType;

public class PlaceHelpfulTypeModel {

    private Long id;
    private HelpfulTypeModel helpfulType;
    private PlaceModel place;

    public static PlaceHelpfulTypeModel toModel(PlaceHelpfulType entity) {
        PlaceHelpfulTypeModel model = new PlaceHelpfulTypeModel();
        model.setId(entity.getId());

        if (entity.getHelpfulType() != null)
            model.setHelpfulType(HelpfulTypeModel.toModel(entity.getHelpfulType()));

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

    public HelpfulTypeModel getHelpfulType() {
        return helpfulType;
    }

    public void setHelpfulType(HelpfulTypeModel helpfulType) {
        this.helpfulType = helpfulType;
    }

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.place = place;
    }

}
