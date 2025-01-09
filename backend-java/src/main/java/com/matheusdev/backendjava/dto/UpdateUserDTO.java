package com.matheusdev.backendjava.dto;

public class UpdateUserDTO {

    private String name;
    private String phoneNumber;
    private String email;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UpdateUserDTO(UserDTO user) {
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
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
