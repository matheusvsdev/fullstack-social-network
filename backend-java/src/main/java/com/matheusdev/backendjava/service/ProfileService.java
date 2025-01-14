package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.CreateUserProfileDTO;
import com.matheusdev.backendjava.dto.ResponseUserProfileDTO;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public ResponseUserProfileDTO create(CreateUserProfileDTO dto) {
        ProfileEntity profile = new ProfileEntity();
        profile.setProfileImage("https://cdn-icons-png.flaticon.com/512/6596/6596121.png");
        profile.setUsername(dto.getUsername());
        profile.setBio("");
        profile.setUser(userService.createUser(dto));

        profileRepository.save(profile);

        return new ResponseUserProfileDTO(profile);
    }

    @Transactional(readOnly = true)
    public ResponseUserProfileDTO findById(String objectId) {
        Optional<ProfileEntity> profile = profileRepository.findById(objectId);
        return new ResponseUserProfileDTO(profile.get());
    }

    @Transactional(readOnly = true)
    public Page<ResponseUserProfileDTO> searchProfilesByUsername(String username, Pageable pageable) {
        if (username == null || username.trim().isEmpty()) {
            return Page.empty(pageable);
        }
        return profileRepository.findByUsername(username, pageable).map(ResponseUserProfileDTO::new);
    }
}
