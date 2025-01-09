package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.PostDTO;
import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.PostRepository;
import com.matheusdev.backendjava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

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
}
