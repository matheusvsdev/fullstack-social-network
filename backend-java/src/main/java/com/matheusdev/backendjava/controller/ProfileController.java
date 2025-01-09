package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.*;
import com.matheusdev.backendjava.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<ResponseUserProfileDTO> save(@RequestBody CreateUserProfileDTO createProfileDTO) {
        ResponseUserProfileDTO profile = profileService.create(createProfileDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{objectId}")
                .buildAndExpand(profile.getObjectId())
                .toUri();

        return ResponseEntity.created(uri).body(profile);
    }

    @GetMapping(value = "/{objectId}")
    public ResponseEntity<ResponseUserProfileDTO> findById(@PathVariable String objectId) {
        ResponseUserProfileDTO profile = profileService.findById(objectId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping(value = "/{objectId}")
    public ResponseEntity<ResponseUserProfileDTO> update(@PathVariable String objectId, @RequestBody ProfileDTO profileDTO) {
        ResponseUserProfileDTO profile = profileService.update(objectId, profileDTO);
        return ResponseEntity.ok().body(profile);
    }
}
