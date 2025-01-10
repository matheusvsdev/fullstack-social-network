package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class FollowersDTO {

    private String id;
    private String imageProfile;
    private String username;

    public FollowersDTO() {
    }

    public FollowersDTO(String id,String imageProfile, String username) {
        this.id = id;
        this.imageProfile = imageProfile;
        this.username = username;
    }

    public FollowersDTO(ProfileEntity entity) {
        this.id = entity.getObjectId();
        this.imageProfile = entity.getProfileImage();
        this.username = entity.getUsername();
    }

    public String getId() {
        return id;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public String getUsername() {
        return username;
    }
}
