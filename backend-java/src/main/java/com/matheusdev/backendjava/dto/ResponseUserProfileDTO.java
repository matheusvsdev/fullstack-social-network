package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class ResponseUserProfileDTO {

    private String objectId;
    private String profileImage;
    private String username;
    private String bio;
    private Long followers;
    private Long following;
    private ResponseUserDTO user;

    public ResponseUserProfileDTO() {
    }

    public ResponseUserProfileDTO(String objectId, String profileImage, String username, String bio, Long followers, Long following, ResponseUserDTO user) {
        this.objectId = objectId;
        this.profileImage = profileImage;
        this.username = username;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.user = user;
    }

    public ResponseUserProfileDTO(ProfileEntity entity) {
        this.objectId = entity.getObjectId();
        this.profileImage = entity.getProfileImage();
        this.username = entity.getUsername();
        this.followers = entity.getFollowers().stream().count();
        this.following = entity.getFollowing().stream().count();
        this.bio = entity.getBio();
        this.user = new ResponseUserDTO(entity.getUser());
    }

    public String getObjectId() {
        return objectId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getUsername() {
        return username;
    }

    public Long getFollowers() {
        return followers;
    }

    public Long getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public ResponseUserDTO getUser() {
        return user;
    }
}
