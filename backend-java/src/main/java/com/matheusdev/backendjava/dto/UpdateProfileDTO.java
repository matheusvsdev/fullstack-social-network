package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.ProfileEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProfileDTO {

    private String profileImage;

    @NotBlank(message = "Required field")
    @Size(min = 3, message = "Minimum 3 characters")
    private String username;

    private String bio;

    public UpdateProfileDTO() {
    }

    public UpdateProfileDTO(String profileImage, String username, String bio) {
        this.profileImage = profileImage;
        this.username = username;
        this.bio = bio;
    }

    public UpdateProfileDTO(ProfileEntity entity) {
        this.profileImage = entity.getProfileImage();
        this.username = entity.getUsername();
        this.bio = entity.getBio();
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }
}
