package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.UserEntity;

public class UserDTO {

    private String fullName;
    private String username;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String fullName, String username, String email, String password) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDTO(UserEntity user) {
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getFullName() {
        return fullName;
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
