package com.matheusdev.backendjava.entities;

import com.matheusdev.backendjava.entities.enums.FollowStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "followRequests")
public class FollowRequest {

    @Id
    private String objectId;

    @DBRef
    private ProfileEntity requester;

    @DBRef
    private ProfileEntity requested;

    private FollowStatus state;

    public FollowRequest() {
    }

    public FollowRequest(String objectId, ProfileEntity requester, ProfileEntity requested, FollowStatus state) {
        this.objectId = objectId;
        this.requester = requester;
        this.requested = requested;
        this.state = state;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public ProfileEntity getRequester() {
        return requester;
    }

    public void setRequester(ProfileEntity requester) {
        this.requester = requester;
    }

    public ProfileEntity getRequested() {
        return requested;
    }

    public void setRequested(ProfileEntity requested) {
        this.requested = requested;
    }

    public FollowStatus getState() {
        return state;
    }

    public void setState(FollowStatus state) {
        this.state = state;
    }
}
