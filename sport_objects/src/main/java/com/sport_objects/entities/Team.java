package com.sport_objects.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String createDate;

    @ManyToOne
    private SportType sportType;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamUser> teamUsers;

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

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public List<TeamUser> getTeamUsers() {
        return teamUsers;
    }

    public void setTeamUsers(List<TeamUser> teamUsers) {
        this.teamUsers = teamUsers;
    }

    public List<String> usersFromTeamUsers() {
        List<String> users = new ArrayList<>();
        for (TeamUser tu : teamUsers)
            users.add(tu.getUser().getLastName() + " " + tu.getUser().getFirstName());

        return users;
    }

    public int usersCount() {
        return teamUsers.size();
    }

    public int activeUsersCount() {
        int count = 0;

        for (TeamUser tu : teamUsers) {
            if (tu.getExitDate().equals(""))
                count += 1;
        }

        return count;
    }

    public String toString() {
        return name == null ? "" : name;
    }
}
