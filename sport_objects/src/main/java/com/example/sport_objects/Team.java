package com.example.sport_objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String createDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SportType> sportTypes;

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

    public Set<SportType> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(Set<SportType> sportTypes) {
        this.sportTypes = sportTypes;
    }

}
