package com.matheusdev.backendjava.config;

import com.matheusdev.backendjava.embedded.Author;
import com.matheusdev.backendjava.embedded.Comment;
import com.matheusdev.backendjava.entities.*;
import com.matheusdev.backendjava.entities.enums.FollowStatus;
import com.matheusdev.backendjava.repository.*;
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

    @Autowired
    private FollowRequestRepository followRequestRepository;

    @PostConstruct
    public void init() {

        userRepository.deleteAll();
        profileRepository.deleteAll();
        postRepository.deleteAll();
        roleRepository.deleteAll();
        followRequestRepository.deleteAll();


        UserEntity maria = new UserEntity(null, "Maria Brown", "9999999", "maria@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity alex = new UserEntity(null, "Alex Green", "8888888", "alex@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity bob = new UserEntity(null, "Bob Blue", "333333", "bob@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity liza = new UserEntity(null, "Liza Yellow", "1111111", "liza@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity bea = new UserEntity(null, "Bea Purple", "22222", "bea@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");
        UserEntity tom = new UserEntity(null, "Tom Orange", "777777", "tom@example.com", "$2a$10$KpjXNGiX3wTz70cMrVtZEuKiduW4YYXv8XuxYYd2.NGXxYvzssHjC");

        RoleEntity user = new RoleEntity(null, "ROLE_USER");
        RoleEntity admin = new RoleEntity(null, "ROLE_ADMIN");

        roleRepository.saveAll(Arrays.asList(user, admin));

        // Associar o papel ao usuário
        maria.getRoles().addAll(Arrays.asList(user, admin));
        alex.getRoles().add(user);
        bob.getRoles().add(admin);
        liza.getRoles().add(user);
        bea.getRoles().add(user);
        tom.getRoles().add(user);

        ProfileEntity profileMaria = new ProfileEntity(null, "https://i.pinimg.com/736x/54/8a/65/548a659c2b06a877516d3c998f5b0939.jpg", "dev.maria", "Dev Sênior", maria);
        ProfileEntity profileAlex = new ProfileEntity(null, "https://cdn-icons-png.flaticon.com/512/6858/6858485.png", "eng.alex", "Engenheiro de Software em @SocialNetwork", alex);
        ProfileEntity profileBob = new ProfileEntity(null, "https://cdn.icon-icons.com/icons2/2643/PNG/512/male_man_boy_black_tone_people_person_avatar_icon_159356.png","java.bob", "Desenvolvedor de Software | Java Spring Boot", bob);
        ProfileEntity profileLiza = new ProfileEntity(null, "https://www.svgrepo.com/show/382096/female-avatar-girl-face-woman-user.svg","tech.liza", "Tech Lead em @Tech", liza);
        ProfileEntity profileBea = new ProfileEntity(null, "https://cdn.icon-icons.com/icons2/2643/PNG/512/female_woman_person_people_avatar_user_white_tone_icon_159359.png","dev.bea", "Desenvolvedora de Software | Python", bea);
        ProfileEntity profileTom = new ProfileEntity(null, "https://cdn-icons-png.flaticon.com/512/145/145931.png","dev.tom", "Desenvolvedor Frontend | React", tom);

        userRepository.saveAll(Arrays.asList(maria, alex, bob, liza, bea, tom));
        profileRepository.saveAll(Arrays.asList(profileMaria, profileAlex, profileBob, profileLiza, profileBea, profileTom));

        // Criar solicitação de seguir de Alex para Maria
        FollowRequest followRequestAlexToMaria = new FollowRequest();
        followRequestAlexToMaria.setRequester(profileAlex);
        followRequestAlexToMaria.setRequested(profileMaria);
        followRequestAlexToMaria.setState(FollowStatus.ACCEPTED); // Aceita a solicitação diretamente
        profileAlex.getFollowing().add(profileMaria);
        profileMaria.getFollowers().add(profileAlex);
        followRequestRepository.save(followRequestAlexToMaria);

        // Criar solicitação de seguir de Maria para Alex
        FollowRequest followRequestMariaToAlex = new FollowRequest();
        followRequestMariaToAlex.setRequester(profileMaria);
        followRequestMariaToAlex.setRequested(profileAlex);
        followRequestMariaToAlex.setState(FollowStatus.ACCEPTED); // Aceita a solicitação diretamente
        profileMaria.getFollowing().add(profileAlex);
        profileAlex.getFollowers().add(profileMaria);
        followRequestRepository.save(followRequestMariaToAlex);

        // Criar solicitação de seguir de Tom para Maria
        FollowRequest followRequestTomToMaria = new FollowRequest();
        followRequestTomToMaria.setRequester(profileTom);
        followRequestTomToMaria.setRequested(profileMaria);
        followRequestTomToMaria.setState(FollowStatus.ACCEPTED); // Aceita a solicitação diretamente
        profileTom.getFollowing().add(profileMaria);
        profileMaria.getFollowers().add(profileTom);
        followRequestRepository.save(followRequestTomToMaria);

        profileRepository.save(profileAlex);
        profileRepository.save(profileMaria);
        profileRepository.save(profileTom);


        PostEntity post1 = new PostEntity(null, "https://www.napratica.org.br/wp-content/uploads/2022/04/Foto-de-fauxels-no-Pexels-27.png", "Modelo de um código bem escrito(contém ironia)", Instant.now().plusSeconds(8000), new Author(profileMaria));
        PostEntity post2 = new PostEntity(null, "https://safesea.com.br/midias/2021/10/marenatureza-1.jpg", "As férias começaram!", Instant.parse("2024-01-11T13:30:00Z"), new Author(profileMaria));
        PostEntity post3 = new PostEntity(null, "https://carreiraemti.com.br/wp-content/uploads/2024/05/Sem-nome-1080-x-1440-px.jpg", "O que acharam desse livro?", Instant.now(), new Author(profileAlex));
        PostEntity post4 = new PostEntity(null, "https://www.gta.ufrj.br/ensino/eel879/trabalhos_v1_2017_2/docker/images/components.png", "Ilustrando um pouco sobre essa ferramenta fantástica!", Instant.now(), new Author(profileBob));
        PostEntity post5 = new PostEntity(null, "https://hermes.dio.me/articles/cover/f54fb5c2-a04f-4ee5-aaf2-14fd4336fbc1.png", "Você já ta usando essas duas ferramentas?", Instant.now(), new Author(profileAlex));

        Comment comment1 = new Comment(profileAlex.getObjectId(), profileAlex.getUsername(), "Nunca vi um código tão fácil de ler", Instant.now().plusSeconds(1800));
        Comment comment2 = new Comment(profileBob.getObjectId(), profileBob.getUsername(), "Baseado no livro Clean Code", Instant.now().plusSeconds(900));
        Comment comment3 = new Comment(profileAlex.getObjectId(), profileAlex.getUsername(), "Aproveite!", Instant.now().plusSeconds(4200));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().add(comment3);

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
