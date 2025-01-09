package com.matheusdev.backendjava.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "profiles")
public class ProfileEntity {

    @Id
    private String objectId;
    private String username;
    private String profileImage;
    private String bio;

    @DBRef
    private UserEntity user;

    @DBRef(lazy = true)
    private List<ProfileEntity> followers = new ArrayList<>();

    @DBRef(lazy = true)
    private List<ProfileEntity> following = new ArrayList<>();

    @DBRef(lazy = true)
    private List<PostEntity> posts = new ArrayList<>();

    public ProfileEntity() {
    }

    public ProfileEntity(String objectId, String username, String profileImage, String bio, UserEntity user) {
        this.objectId = objectId;
        this.username = username;
        this.profileImage = profileImage;
        this.bio = bio;
        this.user = user;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<ProfileEntity> getFollowers() {
        return followers;
    }

    public List<ProfileEntity> getFollowing() {
        return following;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }
}
