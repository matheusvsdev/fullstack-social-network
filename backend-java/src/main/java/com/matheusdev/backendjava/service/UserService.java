package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.entities.User;
import com.matheusdev.backendjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

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

    @Transactional(readOnly = true)
    public List<ResponseUserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(ResponseUserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResponseUserDTO findById(String id) {
        Optional<User> result = userRepository.findById(id);
        User user = result.orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseUserDTO(user);
    }

    @Transactional
    public ResponseUserDTO update(String id, UserDTO userDTO) {
        Optional<User> result = userRepository.findById(id);
        User user = result.orElseThrow(() -> new RuntimeException("User not found"));
        copyDtoToEntity(user, userDTO);
        userRepository.save(user);
        return new ResponseUserDTO(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new org.springframework.security.core.userdetails.User(result.getUsername(), result.getPassword(), new ArrayList<>());
    }

    public boolean checkPassword(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        return passwordEncoder.matches(password, userDetails.getPassword());
    }

    public void copyDtoToEntity(User entity, UserDTO userDTO) {
        entity.setName(userDTO.getName());
        entity.setUsername(userDTO.getUsername());
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
}
