package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.ProfileDTO;
import com.matheusdev.backendjava.dto.ResponseProfileDTO;
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
    public ResponseEntity<ResponseProfileDTO> create(@RequestBody ProfileDTO profileDTO) {
        ResponseProfileDTO profile = profileService.create(profileDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{objectId}")
                .buildAndExpand(profile.getObjectId())
                .toUri();

        return ResponseEntity.created(uri).body(profile);
    }

    @PutMapping(value = "/{objectId}")
    public ResponseEntity<ResponseProfileDTO> update(@PathVariable String objectId, @RequestBody ProfileDTO profileDTO) {
        ResponseProfileDTO profile = profileService.update(objectId, profileDTO);
        return ResponseEntity.ok().body(profile);
    }
}
