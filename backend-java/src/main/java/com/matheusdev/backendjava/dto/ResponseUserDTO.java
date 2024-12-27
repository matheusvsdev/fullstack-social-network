package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.UserEntity;

public class ResponseUserDTO {

    private String objectId;
    private String fullName;
    private String email;

    public ResponseUserDTO() {
    }

    public ResponseUserDTO(String objectId, String fullName, String email) {
        this.objectId = objectId;
        this.fullName = fullName;
        this.email = email;
    }

    public ResponseUserDTO(UserEntity user) {
        this.objectId = user.getObjectId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
    }

    public String getObjectId() {
        return objectId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
