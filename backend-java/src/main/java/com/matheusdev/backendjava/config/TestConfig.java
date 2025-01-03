package com.matheusdev.backendjava.config;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.PostEntity;
import com.matheusdev.backendjava.entities.RoleEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.repository.PostRepository;
import com.matheusdev.backendjava.repository.ProfileRepository;
import com.matheusdev.backendjava.repository.RoleRepository;
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
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {

        userRepository.deleteAll();
        profileRepository.deleteAll();
        postRepository.deleteAll();
        roleRepository.deleteAll();

        UserEntity maria = new UserEntity(null, "Maria Brown", "dev.maria", "maria@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity alex = new UserEntity(null, "Alex Green", "eng.alex", "alex@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity bob = new UserEntity(null, "Bob Blue", "devops.bob", "bob@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");

        RoleEntity user = new RoleEntity(null, "ROLE_USER");
        RoleEntity admin = new RoleEntity(null, "ROLE_ADMIN");

        roleRepository.saveAll(Arrays.asList(user, admin));

        // Associar o papel ao usuário
        maria.getRoles().addAll(Arrays.asList(user, admin));
        alex.getRoles().add(user);
        bob.getRoles().add(admin);

        ProfileEntity profileMaria = new ProfileEntity(null, "http://profileimage", 0L, 0L, "SP", maria);
        ProfileEntity profileAlex = new ProfileEntity(null, "http://profileimage", 0L, 0L, "AL", alex);
        ProfileEntity profileBob = new ProfileEntity(null, "http://profileimage", 0L, 0L, "RJ", bob);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        profileRepository.saveAll(Arrays.asList(profileMaria, profileAlex, profileBob));

        PostEntity post1 = new PostEntity(null, Instant.now(), "Começando as férias", "Finalmente chegou a melhor época do ano!!!", new Author(profileMaria));
        PostEntity post2 = new PostEntity(null, Instant.now(), "Bom dia", "Acordando da melhor forma!", new Author(profileMaria));

        Comment comment1 = new Comment("Boa viagem!", Instant.now().plusSeconds(1800), new Author(profileAlex));
        Comment comment2 = new Comment("Aproveite!", Instant.now().plusSeconds(900), new Author(profileBob));
        Comment comment3 = new Comment("Tenha um ótimo dia!", Instant.now().plusSeconds(4200), new Author(profileAlex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        profileMaria.getPosts().addAll(Arrays.asList(post1, post2));

        profileRepository.save(profileMaria);

        userRepository.save(maria);
    }
}
