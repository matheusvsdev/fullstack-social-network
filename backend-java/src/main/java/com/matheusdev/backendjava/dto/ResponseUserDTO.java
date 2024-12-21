package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.User;

public class ResponseUserDTO {

    private String id;
    private String name;
    private String username;
    private String email;

    public ResponseUserDTO() {
    }

    public ResponseUserDTO(String id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public ResponseUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
