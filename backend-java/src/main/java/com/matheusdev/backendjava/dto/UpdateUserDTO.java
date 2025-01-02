package com.matheusdev.backendjava.dto;

public class UpdateUserDTO {

    private String fullName;
    private String username;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String fullName, String username) {
        this.fullName = fullName;
        this.username = username;
    }

    public UpdateUserDTO(UserDTO user) {
        this.fullName = user.getFullName();
        this.username = user.getUsername();
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }
}
