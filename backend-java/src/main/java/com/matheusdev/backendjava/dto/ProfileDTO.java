package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class ProfileDTO {

    private String profileImage;
    private Long followers;
    private Long following;
    private String bio;
    private UserDTO user;

    public ProfileDTO() {
    }

    public ProfileDTO(String profileImage, Long followers, Long following, String bio, UserDTO user) {
        this.profileImage = profileImage;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.user = user;
    }

    public ProfileDTO(ProfileEntity entity) {
        profileImage = entity.getProfileImage();
        followers = entity.getFollowers();
        following = entity.getFollowing();
        bio = entity.getBio();
        user = new UserDTO(entity.getUser());
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

    public UserDTO getUser() {
        return user;
    }
}
