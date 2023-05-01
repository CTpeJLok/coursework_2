package com.sport_objects.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
public class PlaceSportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sport_type_id")
    private SportType sportType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id")
    private Place place;

    private String size;

    @OneToMany(mappedBy = "placeSportType", cascade = CascadeType.ALL)
    private List<EventPlace> eventPlaces;

    public PlaceSportType(Place place) {
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<EventPlace> getEventPlaces() {
        return eventPlaces;
    }

    public void setEventPlaces(List<EventPlace> eventPlaces) {
        this.eventPlaces = eventPlaces;
    }

    public String toString() {
        return sportType.getName() + " - " + place.getName();
    }
}
