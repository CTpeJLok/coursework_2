package com.sport_objects.models;

import com.sport_objects.entities.Event;

public class EventModel {

    private Long id;
    private String name;
    private String createDate;
    private String updateDate;
    private String cancelDate;
    private String startDate;
    private String endDate;
    private String description;

    public static EventModel toModel(Event entity) {
        EventModel model = new EventModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCreateDate(entity.getCreateDate());
        model.setUpdateDate(entity.getUpdateDate());
        model.setCancelDate(entity.getCancelDate());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
