package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class ProfileDTO {

    private String username;
    private String profileImage;
    private String bio;

    public ProfileDTO() {
    }

    public ProfileDTO(String username, String profileImage, String bio) {
        this.username = username;
        this.profileImage = profileImage;
        this.bio = bio;
    }

    public ProfileDTO(ProfileEntity entity) {
        this.username = entity.getUsername();
        this.profileImage = entity.getProfileImage();
        this.bio = entity.getBio();
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getBio() {
        return bio;
    }
}
