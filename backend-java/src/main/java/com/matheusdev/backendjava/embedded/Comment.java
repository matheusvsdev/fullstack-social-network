package com.matheusdev.backendjava.embedded;

import java.time.Instant;

public class Comment {

    private String author;
    private String text;
    private Instant createdAt;

    public Comment() {
    }

    public Comment(String author, String text, Instant createdAt) {
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
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
