package com.matheusdev.backendjava.config;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.repository.PostRepository;
import com.matheusdev.backendjava.repository.ProfileRepository;
import com.matheusdev.backendjava.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init() {

        userRepository.deleteAll();
        profileRepository.deleteAll();
        postRepository.deleteAll();

        UserEntity user1 = new UserEntity(null, "Maria Brown", "maria@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity user2 = new UserEntity(null, "Alex Green", "alex@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity user3 = new UserEntity(null, "Bob Blue", "bob@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        ProfileEntity profile1 = new ProfileEntity(null, "http://profileimage", "mariabrown123", 0, 0, "SP", user1);
        ProfileEntity profile2 = new ProfileEntity(null, "http://profileimage", "alexgreen", 0, 0, "AL", user2);
        ProfileEntity profile3 = new ProfileEntity(null, "http://profileimage", "boblue", 0, 0, "RJ", user3);

        profileRepository.saveAll(Arrays.asList(profile1, profile2, profile3));

        PostEntity post1 = new PostEntity(null, Instant.now(), "Começando as férias", "Finalmente chegou a melhor época do ano!!!", new Author(profile1));
        PostEntity post2 = new PostEntity(null, Instant.now(), "Bom dia", "Acordando da melhor forma!", new Author(profile1));

        Comment comment1 = new Comment("Boa viagem!", Instant.now().plusSeconds(1800), new Author(profile2));
        Comment comment2 = new Comment("Aproveite!", Instant.now().plusSeconds(900), new Author(profile3));
        Comment comment3 = new Comment("Tenha um ótimo dia!", Instant.now().plusSeconds(4200), new Author(profile2));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        profile1.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(user1);
    }
}
