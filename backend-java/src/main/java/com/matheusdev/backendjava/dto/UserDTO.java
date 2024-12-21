package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.User;

public class UserDTO {

    private String name;
    private String username;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
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

    public String getPassword() {
        return password;
    }
}
