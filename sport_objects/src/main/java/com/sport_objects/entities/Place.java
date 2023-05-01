package com.sport_objects.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String location;

    private String name;

    private String description;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceSportType> placeSportTypes;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceHelpfulType> placeHelpfulTypes;

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

    public List<String> sportTypesFromPlaceSportTypes() {
        List<String> sport_types = new ArrayList<>();
        for (PlaceSportType pst : getPlaceSportTypes())
            sport_types.add(pst.getSportType().getName());

        return sport_types;
    }

    public List<String> helpfulTypesFromPlaceHelpfulTypes() {
        List<String> helpfulTypes = new ArrayList<>();
        for (PlaceHelpfulType pht : getPlaceHelpfulTypes())
            helpfulTypes.add(pht.getHelpfulType().getName());

        return helpfulTypes;
    }

    public List<PlaceSportType> getPlaceSportTypes() {
        return placeSportTypes;
    }

    public void setPlaceSportTypes(List<PlaceSportType> placeSportTypes) {
        this.placeSportTypes = placeSportTypes;
    }

    public List<PlaceHelpfulType> getPlaceHelpfulTypes() {
        return placeHelpfulTypes;
    }

    public void setPlaceHelpfulTypes(List<PlaceHelpfulType> placeHelpfulTypes) {
        this.placeHelpfulTypes = placeHelpfulTypes;
    }
}
