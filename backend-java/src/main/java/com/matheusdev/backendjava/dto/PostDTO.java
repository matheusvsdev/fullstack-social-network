package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.entities.PostEntity;

public class PostDTO {

    private String title;
    private String content;
    private Author author;

    public PostDTO() {
    }

    public PostDTO(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PostDTO(PostEntity post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor() {
        return author;
    }
}
