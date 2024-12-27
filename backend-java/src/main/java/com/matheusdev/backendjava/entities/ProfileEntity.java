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
    private Long followers;
    private Long following;
    private String bio;

    @DBRef
    private UserEntity user;

    @DBRef(lazy = true)
    private List<PostEntity> posts = new ArrayList<>();

    public ProfileEntity() {
    }

    public ProfileEntity(String objectId, String profileImage, Long followers, Long following, String bio, UserEntity user) {
        this.objectId = objectId;
        this.profileImage = profileImage;
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

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
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
