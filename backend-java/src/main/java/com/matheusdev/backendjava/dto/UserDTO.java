package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.UserEntity;

public class UserDTO {

    private String fullName;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public UserDTO(UserEntity user) {
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
