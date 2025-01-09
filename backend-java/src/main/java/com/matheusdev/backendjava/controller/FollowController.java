package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping(value = "/{objectId}")
    public ResponseEntity<?> followUser(@PathVariable String objectId) {
        followService.addFollower(objectId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/unfollow/{objectId}")
    public ResponseEntity<?> unfollowUser(@PathVariable String objectId) {
        followService.unfollowUser(objectId);
        return ResponseEntity.noContent().build();
    }
}
