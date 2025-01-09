package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class CreateUserProfileDTO {

    private String name;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;

    public CreateUserProfileDTO() {
    }

    public CreateUserProfileDTO(String name, String username, String phoneNumber, String email, String password) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public CreateUserProfileDTO(ProfileEntity entity) {
        this.name = entity.getUser().getName();
        this.username = entity.getUsername();
        this.phoneNumber = entity.getUser().getPhoneNumber();
        this.email = entity.getUser().getEmail();
        this.password = entity.getUser().getPassword();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
