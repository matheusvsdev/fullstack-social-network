package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.FollowersDTO;
import com.matheusdev.backendjava.dto.ResponseFollowRequestDTO;
import com.matheusdev.backendjava.entities.FollowRequest;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.entities.enums.FollowStatus;
import com.matheusdev.backendjava.service.exceptions.ArgumentAlreadyExistsException;
import com.matheusdev.backendjava.service.exceptions.ResourceNotFoundException;
import com.matheusdev.backendjava.repository.FollowRequestRepository;
import com.matheusdev.backendjava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private FollowRequestRepository followRequestRepository;

    public void sendFollowRequest(String objectId) {
        ProfileEntity userProfile = getAuthenticatedUserProfile();
        ProfileEntity followUser = profileRepository.findById(objectId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        FollowStatus followStatus = isFollowing(followUser.getObjectId());
        if (followStatus == FollowStatus.ACCEPTED || FollowStatus.PENDING == followStatus) {
            throw new ArgumentAlreadyExistsException("You have already sent the request or are following this user.");
        }
        FollowRequest followRequest = new FollowRequest();
        followRequest.setRequester(userProfile);
        followRequest.setRequested(followUser);
        followRequest.setState(FollowStatus.PENDING);

        followRequestRepository.save(followRequest);
    }

    private ProfileEntity getAuthenticatedUserProfile() {
        UserEntity user = authService.authenticated();
        return profileRepository.findByUser(user);
    }

    public List<ResponseFollowRequestDTO> getPendingFollowRequests() {
        ProfileEntity userProfile = getAuthenticatedUserProfile();
        List<FollowRequest> followRequests = followRequestRepository.findByRequested(userProfile);
        return followRequests.stream().map(ResponseFollowRequestDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FollowersDTO> getFollowers() {
        ProfileEntity userProfile = getAuthenticatedUserProfile();
        return userProfile.getFollowers().stream().map(follower -> new FollowersDTO(follower.getObjectId(), follower.getProfileImage(), follower.getUsername())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FollowersDTO> getFollowing() {
        ProfileEntity userProfile = getAuthenticatedUserProfile();
        return userProfile.getFollowing().stream().map(following -> new FollowersDTO(following.getObjectId(), following.getProfileImage(), following.getUsername())).collect(Collectors.toList());
    }

    public void unfollowUser(String objectId) {
        ProfileEntity userProfile = getAuthenticatedUserProfile();

        ProfileEntity followUser = profileRepository.findById(objectId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userProfile.getFollowing().remove(followUser);
        followUser.getFollowers().remove(userProfile);

        profileRepository.save(userProfile);
        profileRepository.save(followUser);
    }

    public FollowStatus isFollowing(String objectId) {
        ProfileEntity userProfile = getAuthenticatedUserProfile();
        ProfileEntity followUser = profileRepository.findById(objectId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (userProfile.getFollowing().contains(followUser)) {
            return FollowStatus.ACCEPTED;
        }

        FollowRequest followRequest = followRequestRepository.findByRequesterAndRequested(userProfile, followUser);
        if (followRequest != null && followRequest.getState() == FollowStatus.PENDING) {
            return FollowStatus.PENDING;
        }

        return FollowStatus.NONE;
    }

    public void acceptFollowRequest(String requestId) {
        FollowRequest followRequest = followRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada"));
        followRequest.setState(FollowStatus.ACCEPTED);
        ProfileEntity userProfile = followRequest.getRequester();
        ProfileEntity followUser = followRequest.getRequested();
        userProfile.getFollowing().add(followUser);
        followUser.getFollowers().add(userProfile);
        profileRepository.save(userProfile);
        profileRepository.save(followUser);
        followRequestRepository.save(followRequest);
    }

    public void rejectFollowRequest(String requestId) {
        FollowRequest followRequest = followRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada"));
        followRequestRepository.delete(followRequest);
    }
}
