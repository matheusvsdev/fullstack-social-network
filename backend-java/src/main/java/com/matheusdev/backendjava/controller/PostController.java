package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.PostDTO;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostEntity> create(@RequestBody PostDTO post) {
        PostEntity createdPost = postService.create(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll() {
        List<PostDTO> posts = postService.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/following")
    public ResponseEntity<List<PostDTO>> getPostsByFollowing() {
        List<PostDTO> posts = postService.findPostsByFollowing();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/own-posts")
    public ResponseEntity<List<PostDTO>> getOwnPosts() {
        List<PostDTO> posts = postService.findOwnPosts();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/profile-posts/{objectId}")
    public ResponseEntity<List<PostDTO>> getProfilePosts(@PathVariable String objectId) {
        List<PostDTO> profilePosts = postService.findProfilePosts(objectId);
        return ResponseEntity.ok().body(profilePosts);
    }

    @PostMapping(value = "/comment/{objectId}")
    public ResponseEntity<Comment> createComment(@PathVariable String objectId, @RequestBody Comment comment) {
        Comment createdPost = postService.createComment(objectId, comment);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}
