package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class UpdateUserProfileDTO {

    private String profileImage;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;

    public UpdateUserProfileDTO() {
    }

    public UpdateUserProfileDTO(String profileImage, String name, String username, String email, String phoneNumber) {
        this.profileImage = profileImage;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UpdateUserProfileDTO(ProfileEntity entity) {
        this.profileImage = entity.getProfileImage();
        this.name = entity.getUser().getName();
        this.username = entity.getUsername();
        this.email = entity.getUsername();
        this.phoneNumber = entity.getUser().getPhoneNumber();
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
