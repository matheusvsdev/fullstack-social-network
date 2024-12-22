package com.matheusdev.backendjava.embedded;

import java.time.Instant;

public class Comment {

    private String text;
    private Instant createdAt;

    private Author author;

    public Comment() {
    }

    public Comment(String text, Instant createdAt, Author author) {
        this.text = text;
        this.createdAt = createdAt;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
