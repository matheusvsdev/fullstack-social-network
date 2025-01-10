package com.matheusdev.backendjava.dto;

import com.matheusdev.backendjava.entities.FollowRequest;
import com.matheusdev.backendjava.entities.enums.FollowStatus;

public class ResponseFollowRequestDTO {

    private String objectId;
    private String username;
    private FollowStatus state;

    public ResponseFollowRequestDTO() {
    }

    public ResponseFollowRequestDTO(String objectId, String username, FollowStatus state) {
        this.objectId = objectId;
        this.username = username;
        this.state = state;
    }

    public ResponseFollowRequestDTO(FollowRequest entity) {
        this.objectId = entity.getObjectId();
        this.username = entity.getRequester().getUsername();
        this.state = entity.getState();
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }

    public FollowStatus getState() {
        return state;
    }
}
