package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.entities.PostEntity;

public class PostDTO {

    private String imageUrl;
    private String description;
    private Author author;

    public PostDTO() {
    }

    public PostDTO(String imageUrl, String description, Author author) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.author = author;
    }

    public PostDTO(PostEntity post) {
        this.imageUrl = post.getImage();
        this.description = post.getDescription();
        this.author = post.getAuthor();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }
}
