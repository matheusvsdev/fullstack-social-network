package com.matheusdev.backendjava.dto;

public class ProfileDTO {

    private String profileImage;
    private String username;
    private Integer followers;
    private Integer following;
    private String bio;
    private UserDTO user;

    public ProfileDTO() {
    }

    public ProfileDTO(String profileImage, String username, Integer followers, Integer following, String bio, UserDTO user) {
        this.profileImage = profileImage;
        this.username = username;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.user = user;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getUsername() {
        return username;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public UserDTO getUser() {
        return user;
    }
}
