package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.UserEntity;

public class ResponseUserDTO {

    private String objectId;
    private String name;
    private String phoneNumber;
    private String email;

    public ResponseUserDTO() {
    }

    public ResponseUserDTO(String objectId, String name, String phoneNumber, String email) {
        this.objectId = objectId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ResponseUserDTO(UserEntity user) {
        this.objectId = user.getObjectId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
    }

    public String getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
