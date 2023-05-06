package com.sport_objects.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Transient
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<EventDistribution> eventDistributionList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventDistribution> getEventDistributionList() {
        return eventDistributionList;
    }

    public void setEventDistributionList(List<EventDistribution> eventDistributionList) {
        this.eventDistributionList = eventDistributionList;
    }

    public String toString() {
        return name == null ? "" : name;
    }
}
