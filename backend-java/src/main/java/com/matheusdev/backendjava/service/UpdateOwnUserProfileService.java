package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.*;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.PostRepository;
import com.matheusdev.backendjava.repository.ProfileRepository;
import com.matheusdev.backendjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UpdateOwnUserProfileService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public ResponseUserProfileDTO updateSelf(UpdateUserProfileDTO dto) {
        UserEntity user = updateUserSelf(dto);

        ProfileEntity profile = profileRepository.findByUser(user);
        profile.setProfileImage(dto.getProfileImage());
        profile.setUsername(dto.getUsername());
        profile.setUser(user);
        profileRepository.save(profile);

        // Atualizar os posts associados
        updateAuthorInPosts(profile);

        return new ResponseUserProfileDTO(profile);
    }

    private UserEntity updateUserSelf(UpdateUserProfileDTO dto) {
        UserEntity user = authService.authenticated();
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    public void updateAuthorInPosts(ProfileEntity profileEntity) {
        List<PostEntity> posts = postRepository.findByAuthorObjectId(profileEntity.getObjectId());
        for (PostEntity post : posts) {
            post.getAuthor().setImageProfile(profileEntity.getProfileImage());
            post.getAuthor().setUsername(profileEntity.getUsername());
            postRepository.save(post);
        }
    }
}
