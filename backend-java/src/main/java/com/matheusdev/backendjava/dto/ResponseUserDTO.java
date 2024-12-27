package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.UserEntity;

public class ResponseUserDTO {

    private String objectId;
    private String fullName;
    private String username;
    private String email;

    public ResponseUserDTO() {
    }

    public ResponseUserDTO(String objectId, String fullName, String username, String email) {
        this.objectId = objectId;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
    }

    public ResponseUserDTO(UserEntity user) {
        this.objectId = user.getObjectId();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getObjectId() {
        return objectId;
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
}
