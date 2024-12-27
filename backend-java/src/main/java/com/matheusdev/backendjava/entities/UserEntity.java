package com.matheusdev.backendjava.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
public class UserEntity implements UserDetails {

    @Id
    private String objectId;
    private String fullName;
    private String email;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String objectId, String fullName, String email, String password) {
        this.objectId = objectId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Métodos da interface UserDetails

    @Override
    public String getUsername() {
        ProfileEntity profile = new ProfileEntity();
        return profile.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
