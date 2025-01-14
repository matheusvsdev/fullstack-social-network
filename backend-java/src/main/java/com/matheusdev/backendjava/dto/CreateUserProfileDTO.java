package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserProfileDTO {

    @NotBlank(message = "Required field")
    @Size(min = 3, message = "Minimum 3 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "The name must contain only letters")
    private String name;

    @NotBlank(message = "Required field")
    @Size(min = 3, message = "Minimum 3 characters")
    private String username;

    private String phoneNumber;

    @NotBlank(message = "Required field")
    @Pattern(regexp = ".+@.+\\..+", message = "Email must have a valid domain")
    private String email;

    @NotBlank(message = "Required field")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
            , message = "Password must be at least 8 characters long, including one uppercase letter, one lowercase letter, and one number")
    private String password;

    public CreateUserProfileDTO() {
    }

    public CreateUserProfileDTO(String name, String username, String phoneNumber, String email, String password) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public CreateUserProfileDTO(ProfileEntity entity) {
        this.name = entity.getUser().getName();
        this.username = entity.getUsername();
        this.phoneNumber = entity.getUser().getPhoneNumber();
        this.email = entity.getUser().getEmail();
        this.password = entity.getUser().getPassword();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
