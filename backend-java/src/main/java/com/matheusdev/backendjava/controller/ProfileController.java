package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.*;
import com.matheusdev.backendjava.service.ProfileService;
import com.matheusdev.backendjava.service.UpdateOwnUserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UpdateOwnUserProfileService updateOwnUserProfileService;

    @PostMapping
    public ResponseEntity<ResponseUserProfileDTO> create(@Valid @RequestBody CreateUserProfileDTO createProfileDTO) {
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

    @PutMapping(value = "/me")
    public ResponseEntity<ResponseUserProfileDTO> updateSelf(@RequestBody UpdateUserProfileDTO dto) {
        ResponseUserProfileDTO newDto = updateOwnUserProfileService.updateSelf(dto);
        return ResponseEntity.ok().body(newDto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<ResponseUserProfileDTO>> searchProfiles(@RequestParam(required = false) String username,
                                                                       Pageable pageable) {
        Page<ResponseUserProfileDTO> search = profileService.searchProfilesByUsername(username, pageable);
        return ResponseEntity.ok().body(search);
    }
}
