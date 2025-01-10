package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.FollowersDTO;
import com.matheusdev.backendjava.dto.ResponseFollowRequestDTO;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.enums.FollowStatus;
import com.matheusdev.backendjava.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping(value = "/follow/{objectId}")
    public ResponseEntity<?> sendFollowRequest(@PathVariable String objectId) {
        followService.sendFollowRequest(objectId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/follow/pending-requests")
    public ResponseEntity<List<ResponseFollowRequestDTO>> getPendingFollowRequests() {
        List<ResponseFollowRequestDTO> pendingFollowRequests = followService.getPendingFollowRequests();
        return ResponseEntity.ok(pendingFollowRequests);
    }

    @GetMapping(value = "/followers")
    public ResponseEntity<List<FollowersDTO>> getFollowers() {
        List<FollowersDTO> followers = followService.getFollowers();
        return ResponseEntity.ok(followers);
    }

    @GetMapping(value = "/following")
    public ResponseEntity<List<FollowersDTO>> getFollowing() {
        List<FollowersDTO> following = followService.getFollowing();
        return ResponseEntity.ok(following);
    }

    @GetMapping(value = "/follow/{objectId}/isFollowing")
    public ResponseEntity<FollowStatus> isFollowing(@PathVariable String objectId) {
        FollowStatus followStatus = followService.isFollowing(objectId);
        return ResponseEntity.ok(followStatus);
    }

    @PostMapping(value = "/follow/accept/{requestId}")
    public ResponseEntity<?> acceptFollowRequest(@PathVariable String requestId) {
        followService.acceptFollowRequest(requestId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/follow/reject/{requestId}")
    public ResponseEntity<?> rejectFollowRequest(@PathVariable String requestId) {
        followService.rejectFollowRequest(requestId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/unfollow/{objectId}")
    public ResponseEntity<?> unfollowUser(@PathVariable String objectId) {
        followService.unfollowUser(objectId);

        return ResponseEntity.ok().build();
    }
}
