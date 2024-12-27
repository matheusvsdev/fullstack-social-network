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
    private String profileImage;
    private String username;
    private Integer followers;
    private Integer following;
    private String bio;

    @DBRef
    private UserEntity user;

    @DBRef(lazy = true)
    private List<PostEntity> posts = new ArrayList<>();

    public ProfileEntity() {
    }

    public ProfileEntity(String objectId, String profileImage, String username, Integer followers, Integer following, String bio, UserEntity user) {
        this.objectId = objectId;
        this.profileImage = profileImage;
        this.username = username;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.user = user;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
