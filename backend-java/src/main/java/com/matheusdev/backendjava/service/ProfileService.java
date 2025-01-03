package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.ProfileDTO;
import com.matheusdev.backendjava.dto.ResponseProfileDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public ResponseProfileDTO create(UserDTO userDTO) {
        ProfileEntity profile = new ProfileEntity();
        profile.setProfileImage("");
        profile.setBio("");
        profile.setFollowers(0L);
        profile.setFollowing(0L);
        profile.setUser(userService.insert(userDTO));
        profileRepository.save(profile);

        return new ResponseProfileDTO(profile);
    }

    @Transactional
    public ResponseProfileDTO update(String objectId, ProfileDTO profileDTO) {
        Optional<ProfileEntity> result = profileRepository.findById(objectId);
        ProfileEntity profile = result.orElseThrow(() -> new RuntimeException("User not found"));
        copyDtoToEntity(profile, profileDTO);
        profileRepository.save(profile);
        return new ResponseProfileDTO(profile);
    }

    public void copyDtoToEntity(ProfileEntity entity, ProfileDTO profileDTO) {
        entity.setProfileImage(profileDTO.getProfileImage());
        entity.setBio(profileDTO.getBio());
    }
}
