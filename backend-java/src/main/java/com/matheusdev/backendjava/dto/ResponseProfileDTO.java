package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class ResponseProfileDTO {

    private String objectId;
    private String profileImage;
    private Long followers;
    private Long following;
    private String bio;
    private ResponseUserDTO user;

    public ResponseProfileDTO() {
    }

    public ResponseProfileDTO(String objectId, String profileImage, Long followers, Long following, String bio, ResponseUserDTO user) {
        this.objectId = objectId;
        this.profileImage = profileImage;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.user = user;
    }

    public ResponseProfileDTO(ProfileEntity entity) {
        this.objectId = entity.getObjectId();
        this.profileImage = entity.getProfileImage();
        this.followers = entity.getFollowers();
        this.following = entity.getFollowing();
        this.bio = entity.getBio();
        this.user = new ResponseUserDTO(entity.getUser());
    }

    public String getObjectId() {
        return objectId;
    }

    public String getProfileImage() {
        return profileImage;
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
