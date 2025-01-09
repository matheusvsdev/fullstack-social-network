package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.CreateUserProfileDTO;
import com.matheusdev.backendjava.dto.ProfileDTO;
import com.matheusdev.backendjava.dto.ResponseUserProfileDTO;
import com.matheusdev.backendjava.dto.UpdateUserProfileDTO;
import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.RoleEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.ProfileRepository;
import com.matheusdev.backendjava.repository.RoleRepository;
import com.matheusdev.backendjava.repository.UserRepository;
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
    public ResponseUserProfileDTO create(CreateUserProfileDTO dto) {
        ProfileEntity profile = new ProfileEntity();
        profile.setUsername(dto.getUsername());
        profile.setUser(userService.createUser(dto));

        profileRepository.save(profile);

        return new ResponseUserProfileDTO(profile);
    }

    @Transactional(readOnly = true)
    public ResponseUserProfileDTO findById(String objectId) {
        Optional<ProfileEntity> profile = profileRepository.findById(objectId);
        return new ResponseUserProfileDTO(profile.get());
    }

    @Transactional
    public ResponseUserProfileDTO update(String objectId, ProfileDTO profileDTO) {
        Optional<ProfileEntity> result = profileRepository.findById(objectId);
        ProfileEntity profile = result.orElseThrow(() -> new RuntimeException("User not found"));
        copyDtoToEntity(profile, profileDTO);

        profileRepository.save(profile);
        return new ResponseUserProfileDTO(profile);
    }

    public void copyDtoToEntity(ProfileEntity entity, ProfileDTO profileDTO) {
        entity.setUsername(profileDTO.getUsername());
        entity.setProfileImage(profileDTO.getProfileImage());
        entity.setBio(profileDTO.getBio());
    }
}
