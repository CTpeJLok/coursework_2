package com.sport_objects.models;

import com.sport_objects.entities.EventPlace;

public class EventPlaceModel {

    private Long id;
    private String eventDate;
    private String duration;
    private EventModel event;
    private PlaceSportTypeModel placeSportType;

    public static EventPlaceModel toModel(EventPlace entity) {
        EventPlaceModel model = new EventPlaceModel();
        model.setId(entity.getId());
        model.setEventDate(entity.getEventDate());
        model.setDuration(entity.getDuration());

        if (entity.getEvent() != null)
            model.setEvent(EventModel.toModel(entity.getEvent()));

        if (entity.getPlaceSportType() != null)
            model.setPlaceSportType(PlaceSportTypeModel.toModel(entity.getPlaceSportType()));

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    public PlaceSportTypeModel getPlaceSportType() {
        return placeSportType;
    }

    public void setPlaceSportType(PlaceSportTypeModel placeSportType) {
        this.placeSportType = placeSportType;
    }
}
