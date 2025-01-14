package com.matheusdev.backendjava.embedded;

import com.matheusdev.backendjava.entities.ProfileEntity;

import java.time.Instant;

public class Comment {

    private String objectId;
    private String author;
    private String text;
    private Instant createdAt;

    public Comment() {
    }

    public Comment(String objectId, String author, String text, Instant createdAt) {
        this.objectId = objectId;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Comment(ProfileEntity profileEntity) {
        this.objectId = profileEntity.getObjectId();
        this.author = profileEntity.getUsername();
        this.text = getText();
        this.createdAt = Instant.now();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
