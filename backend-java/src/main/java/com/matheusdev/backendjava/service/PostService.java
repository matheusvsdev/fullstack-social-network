package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.PostDTO;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostEntity create(PostDTO postDTO) {
        PostEntity post = new PostEntity();
        post.setImage(postDTO.getImageUrl());
        post.setDescription(postDTO.getDescription());
        post.setCreatedAt(Instant.now());
        post.setAuthor(postDTO.getAuthor());
        return postRepository.save(post);
    }
}
