package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.PostEntity;

import java.util.List;

public class PostDTO {

    private String imageUrl;
    private String caption;
    private Author author;
    private List<Comment> comments;

    public PostDTO() {
    }

    public PostDTO(String imageUrl, String caption, Author author) {
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.author = author;
    }

    public PostDTO(PostEntity post) {
        this.imageUrl = post.getImageUrl();
        this.caption = post.getCaption();
        this.author = post.getAuthor();
        this.comments = post.getComments();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
