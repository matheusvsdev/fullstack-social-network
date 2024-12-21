package com.matheusdev.backendjava.config;

import com.matheusdev.backendjava.entities.User;
import com.matheusdev.backendjava.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        try {
            userRepository.deleteAll();

            User user1 = new User(null, "Maria Brown", "mariabrown", "maria@example.com", "1234");
            User user2 = new User(null, "Alex Green", "alexgreen", "alex@example.com", "1234");
            User user3 = new User(null, "Bob Blue", "boblue", "bob@example.com", "1234");

            userRepository.saveAll(Arrays.asList(user1, user2, user3));
        } catch (Exception e) {
            System.out.println("Erro no método init: " + e.getMessage());
        }
    }
}
