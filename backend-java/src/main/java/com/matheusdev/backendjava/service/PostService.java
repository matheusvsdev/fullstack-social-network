package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.PostDTO;
import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.service.exceptions.ResourceNotFoundException;
import com.matheusdev.backendjava.repository.PostRepository;
import com.matheusdev.backendjava.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public PostEntity create(PostDTO postDTO) {

        //Serviço de autenticação de usuário
        UserEntity user = authService.authenticated();

        ProfileEntity profile = profileRepository.findByUser(user);

        Author author = new Author(profile);

        PostEntity post = new PostEntity();
        post.setImageUrl(postDTO.getImageUrl());
        post.setCaption(postDTO.getCaption());
        post.setCreatedAt(Instant.now());
        post.setAuthor(author);

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findAll() {
        List<PostEntity> users = postRepository.findAll();
        return users.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findPostsByFollowing() {
        UserEntity user = authService.authenticated();

        ProfileEntity profile = profileRepository.findByUser(user);

        List<String> followingIds = profile.getFollowing().stream().map(ProfileEntity::getObjectId).collect(Collectors.toList());
        logger.info("Following Profile IDs: {}", followingIds);

        List<PostEntity> posts = postRepository.findPostsByProfileFollowing(followingIds);
        logger.info("Posts found: {}", posts);

        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findOwnPosts() {
        UserEntity user = authService.authenticated();
        ProfileEntity ownProfile = profileRepository.findByUser(user);

        List<PostEntity> userPosts = postRepository.findByAuthorObjectId(ownProfile.getObjectId());
        return userPosts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findProfilePosts(String objectId) {
        UserEntity user = authService.authenticated();
        ProfileEntity profile = profileRepository.findById(objectId).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        List<PostEntity> userPosts = postRepository.findByAuthorObjectId(profile.getObjectId());
        return userPosts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Comment createComment(String objectId, Comment comment) {

        //Serviço de autenticação de usuário
        UserEntity user = authService.authenticated();

        ProfileEntity profile = profileRepository.findByUser(user);

        Author author = new Author(profile);

        comment.setObjectId(author.getObjectId());
        comment.setAuthor(author.getUsername());
        comment.setCreatedAt(Instant.now());

        PostEntity post = postRepository.findById(objectId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.getComments().add(comment);
        postRepository.save(post);

        return comment;
    }
}
