package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.RoleEntity;

public class RoleDTO {

    private String objectId;

    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(String objectId, String authority) {
        this.objectId = objectId;
        this.authority = authority;
    }

    public RoleDTO(RoleEntity role) {
        objectId = role.getObjectId();
        authority = role.getAuthority();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}