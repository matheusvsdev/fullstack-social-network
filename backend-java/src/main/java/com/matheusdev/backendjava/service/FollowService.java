package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthService authService;

    public void addFollower(String objectId) {

        //Serviço de autenticação de usuário
        UserEntity user = authService.authenticated();

        ProfileEntity userProfile = profileRepository.findByUser(user);

        ProfileEntity followUser = profileRepository.findByObjectId(objectId);

        userProfile.getFollowing().add(followUser);
        followUser.getFollowers().add(userProfile);

        profileRepository.save(userProfile);
        profileRepository.save(followUser);
    }

    public void unfollowUser(String objectId) {
        // Serviço de autenticação de usuário
        UserEntity user = authService.authenticated();

        ProfileEntity userProfile = profileRepository.findByUser(user);

        ProfileEntity followUser = profileRepository.findByObjectId(objectId);

        // Se está seguindo, remove o usuário da lista de seguindo
        userProfile.getFollowing().remove(followUser);
        followUser.getFollowers().remove(user);

        profileRepository.save(userProfile);
        profileRepository.save(followUser);
    }
}
