package com.sport_objects.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "sportType")
    private List<Team> teamList;

    public SportType() {

    }

    public SportType(Long id) {
        this.id = id;
    }

    public SportType(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
}
