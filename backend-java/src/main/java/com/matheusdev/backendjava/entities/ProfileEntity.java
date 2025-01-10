package com.matheusdev.backendjava.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "profiles")
public class ProfileEntity {

    @Id
    private String objectId;
    private String profileImage;
    private String username;
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

    public ProfileEntity(String objectId, String profileImage, String username, String bio, UserEntity user) {
        this.objectId = objectId;
        this.profileImage = profileImage;
        this.username = username;
        this.bio = bio;
        this.user = user;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProfileEntity profile = (ProfileEntity) o;
        return Objects.equals(objectId, profile.objectId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectId);
    }
}
