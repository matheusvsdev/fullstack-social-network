package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.entities.User;
import com.matheusdev.backendjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public ResponseUserDTO insert(UserDTO userDTO) {
        User user = new User();
        copyDtoToEntity(user, userDTO);
        userRepository.save(user);
        return new ResponseUserDTO(user);
    }

    public void copyDtoToEntity(User entity, UserDTO userDTO) {
        entity.setName(userDTO.getName());
        entity.setUsername(userDTO.getUsername());
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
}
