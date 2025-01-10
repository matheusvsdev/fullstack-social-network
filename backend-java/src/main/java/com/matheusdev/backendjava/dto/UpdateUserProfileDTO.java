package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class UpdateUserProfileDTO {

    private String profileImage;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;

    public UpdateUserProfileDTO() {
    }

    public UpdateUserProfileDTO(String profileImage, String username,  String name, String email, String phoneNumber) {
        this.profileImage = profileImage;
        this.username = username;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UpdateUserProfileDTO(ProfileEntity entity) {
        this.profileImage = entity.getProfileImage();
        this.username = entity.getUsername();
        this.name = entity.getUser().getName();
        this.email = entity.getUsername();
        this.phoneNumber = entity.getUser().getPhoneNumber();
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
