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

        UserEntity maria = new UserEntity(null, "Maria Brown", "9999999", "maria@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity alex = new UserEntity(null, "Alex Green", "8888888", "alex@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity bob = new UserEntity(null, "Bob Blue", "333333", "bob@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");

        RoleEntity user = new RoleEntity(null, "ROLE_USER");
        RoleEntity admin = new RoleEntity(null, "ROLE_ADMIN");

        roleRepository.saveAll(Arrays.asList(user, admin));

        // Associar o papel ao usuário
        maria.getRoles().addAll(Arrays.asList(user, admin));
        alex.getRoles().add(user);
        bob.getRoles().add(admin);

        ProfileEntity profileMaria = new ProfileEntity(null, "dev.maria", "https://i.pinimg.com/736x/54/8a/65/548a659c2b06a877516d3c998f5b0939.jpg", "Natal - RN", maria);
        ProfileEntity profileAlex = new ProfileEntity(null, "eng.alex", "https://cdn-icons-png.flaticon.com/512/6858/6858485.png", "São Paulo - SP", alex);
        ProfileEntity profileBob = new ProfileEntity(null, "java.bob", "https://cdn.icon-icons.com/icons2/2643/PNG/512/male_man_boy_black_tone_people_person_avatar_icon_159356.png", "Rio de Janeiro - RJ", bob);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        profileRepository.saveAll(Arrays.asList(profileMaria, profileAlex, profileBob));

        PostEntity post1 = new PostEntity(null, Instant.now(), "https://www.napratica.org.br/wp-content/uploads/2022/04/Foto-de-fauxels-no-Pexels-27.png", "Modelo de um código bem escrito(contém ironia)", new Author(profileMaria));
        PostEntity post2 = new PostEntity(null, Instant.now(), "https://safesea.com.br/midias/2021/10/marenatureza-1.jpg", "As férias começaram!", new Author(profileMaria));
        PostEntity post3 = new PostEntity(null, Instant.now(), "https://carreiraemti.com.br/wp-content/uploads/2024/05/Sem-nome-1080-x-1440-px.jpg", "O que acharam desse livro?", new Author(profileAlex));
        PostEntity post4 = new PostEntity(null, Instant.now(), "https://www.gta.ufrj.br/ensino/eel879/trabalhos_v1_2017_2/docker/images/components.png", "Ilustrando um pouco sobre essa ferramenta fantástica!", new Author(profileBob));
        PostEntity post5 = new PostEntity(null, Instant.now(), "https://hermes.dio.me/articles/cover/f54fb5c2-a04f-4ee5-aaf2-14fd4336fbc1.png", "Você já ta usando essas duas ferramentas?", new Author(profileAlex));

        Comment comment1 = new Comment(profileAlex.getUsername(), "Boa viagem!", Instant.now().plusSeconds(1800));
        Comment comment2 = new Comment(profileBob.getUsername(), "Aproveite!", Instant.now().plusSeconds(900));
        Comment comment3 = new Comment(profileAlex.getUsername(), "Tenha um ótimo dia!", Instant.now().plusSeconds(4200));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));

        profileMaria.getPosts().addAll(Arrays.asList(post1, post2));
        profileBob.getPosts().add(post4);
        profileAlex.getPosts().addAll(Arrays.asList(post3, post5));

        profileRepository.save(profileMaria);
        profileRepository.save(profileBob);
        profileRepository.save(profileAlex);

        userRepository.save(maria);
    }
}
