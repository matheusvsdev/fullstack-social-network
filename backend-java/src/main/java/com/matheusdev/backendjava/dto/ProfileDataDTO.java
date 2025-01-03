package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;

public class ProfileDataDTO {

    private String profileImage;
    private Long followers;
    private Long following;
    private String fullName;
    private String username;
    private String bio;

    public ProfileDataDTO() {
    }

    public ProfileDataDTO(String profileImage, Long followers, Long following, String fullName, String username, String bio) {
        this.profileImage = profileImage;
        this.followers = followers;
        this.following = following;
        this.fullName = fullName;
        this.username = username;
        this.bio = bio;
    }

    public ProfileDataDTO(ProfileEntity entity) {
        this.profileImage = entity.getProfileImage();
        this.followers = entity.getFollowers();
        this.following = entity.getFollowing();
        this.fullName = entity.getUser().getFullName();
        this.username = entity.getUser().getUsername();
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

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }
}
