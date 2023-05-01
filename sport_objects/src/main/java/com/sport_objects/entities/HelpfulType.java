package com.sport_objects.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class HelpfulType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "helpfulType", cascade = CascadeType.ALL)
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

    public String toString() {
        return name;
    }

    public List<PlaceHelpfulType> getPlaceHelpfulTypes() {
        return placeHelpfulTypes;
    }

    public void setPlaceHelpfulTypes(List<PlaceHelpfulType> placeHelpfulTypes) {
        this.placeHelpfulTypes = placeHelpfulTypes;
    }
}
