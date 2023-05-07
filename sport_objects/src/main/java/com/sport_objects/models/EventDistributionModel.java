package com.sport_objects.models;

import com.sport_objects.entities.EventDistribution;

public class EventDistributionModel {

    private Long id;
    private int prize;
    private EventModel event;
    private TeamModel team;
    private UserModel user;
    private PositionModel position;

    public static EventDistributionModel toModel(EventDistribution entity) {
        EventDistributionModel model = new EventDistributionModel();
        model.setId(entity.getId());
        model.setPrize(entity.getPrize());

        if (entity.getEvent() != null)
            model.setEvent(EventModel.toModel(entity.getEvent()));

        if (entity.getPosition() != null)
            model.setPosition(PositionModel.toModel(entity.getPosition()));

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

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
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

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

}
