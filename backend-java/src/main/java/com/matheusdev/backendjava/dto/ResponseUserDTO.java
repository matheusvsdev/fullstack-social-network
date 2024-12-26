package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.User;

public class ResponseUserDTO {

    private String id;
    private String profileImage;
    private String name;
    private String bio;
    private String username;
    private String email;

    public ResponseUserDTO() {
    }

    public ResponseUserDTO(String id, String profileImage, String name, String bio, String username, String email) {
        this.id = id;
        this.profileImage = profileImage;
        this.name = name;
        this.bio = bio;
        this.username = username;
        this.email = email;
    }

    public ResponseUserDTO(User user) {
        this.id = user.getId();
        this.profileImage = user.getProfileImage();
        this.name = user.getName();
        this.bio = user.getBio();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
