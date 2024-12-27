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
    private String title;
    private String content;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public PostEntity() {
    }

    public PostEntity(String objectId, Instant createdAt, String title, String content, Author author) {
        this.objectId = objectId;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
