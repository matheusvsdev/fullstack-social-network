package com.matheusdev.backendjava.embedded;

import com.matheusdev.backendjava.entities.ProfileEntity;

public class Author {

    private String objectId;
    private String username;

    public Author() {
    }

    public Author(String objectId, String username) {
        this.objectId = objectId;
        this.username = username;
    }

    public Author(ProfileEntity entity) {
        this.objectId = entity.getObjectId();
        this.username = entity.getUsername();
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
