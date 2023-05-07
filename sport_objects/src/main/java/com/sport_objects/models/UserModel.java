package com.sport_objects.models;

import com.sport_objects.entities.User;

public class UserModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String birthday;
    private String phone;
    private String email;

    public static UserModel toModel(User entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setBirthday(entity.getBirthday());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
