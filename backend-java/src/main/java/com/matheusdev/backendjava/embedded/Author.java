package com.matheusdev.backendjava.embedded;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class Author {

    private String objectId;
    private String imageProfile;
    private String username;

    public Author() {
    }

    public Author(String objectId, String imageProfile, String username) {
        this.objectId = objectId;
        this.imageProfile = imageProfile;
        this.username = username;
    }

    public Author(ProfileEntity entity) {
        this.objectId = entity.getObjectId();
        this.imageProfile = entity.getProfileImage();
        this.username = entity.getUsername();
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public void updateFromProfile(ProfileEntity profileEntity) {
        if (profileEntity != null) {
            this.imageProfile = profileEntity.getProfileImage();
            this.username = profileEntity.getUsername();
        }
    }
}
