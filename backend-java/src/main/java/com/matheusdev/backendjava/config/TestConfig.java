package com.matheusdev.backendjava.config;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.Post;
import com.matheusdev.backendjava.entities.User;
import com.matheusdev.backendjava.repository.PostRepository;
import com.matheusdev.backendjava.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init() {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User user1 = new User(null, "www.image.com", "Maria Brown", "null", "mariabrown", "maria@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        User user2 = new User(null, "www.image.com", "Alex Green", "null", "alexgreen", "alex@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        User user3 = new User(null, "www.image.com", "Bob Blue", "null", "boblue", "bob@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Post post1 = new Post(null, Instant.now(), "Começando as férias", "Finalmente chegou a melhor época do ano!!!", new Author(user1));
        Post post2 = new Post(null, Instant.now(), "Bom dia", "Acordando da melhor forma!", new Author(user1));

        Comment comment1 = new Comment("Boa viagem!", Instant.now().plusSeconds(1800), new Author(user2));
        Comment comment2 = new Comment("Aproveite!", Instant.now().plusSeconds(900), new Author(user3));
        Comment comment3 = new Comment("Tenha um ótimo dia!", Instant.now().plusSeconds(4200), new Author(user2));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        user1.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(user1);
    }
}
