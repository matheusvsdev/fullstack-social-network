package com.matheusdev.backendjava.entities;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
public class PostEntity {

    @Id
    private String objectId;
    private Instant createdAt;
    private String image;
    private String description;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public PostEntity() {
    }

    public PostEntity(String objectId, Instant createdAt, String image, String description, Author author) {
        this.objectId = objectId;
        this.createdAt = createdAt;
        this.image = image;
        this.description = description;
        this.author = author;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
