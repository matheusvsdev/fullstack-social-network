package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.CreateUserProfileDTO;
import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.RoleDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.entities.RoleEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<ResponseUserDTO> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(ResponseUserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResponseUserDTO findById(String objectId) {
        Optional<UserEntity> result = userRepository.findById(objectId);
        UserEntity user = result.orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseUserDTO(user);
    }

    @Transactional
    public ResponseUserDTO update(String objectId, UserDTO userDTO) {
        Optional<UserEntity> result = userRepository.findById(objectId);
        UserEntity user = result.orElseThrow(() -> new RuntimeException("User not found"));
        copyDtoToEntity(user, userDTO);
        userRepository.save(user);
        return new ResponseUserDTO(user);
    }

    public void delete(String objectId) {
        userRepository.deleteById(objectId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsernameAndRoles(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return user;
    }

    public UserEntity createUser(CreateUserProfileDTO dto) {
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        RoleEntity defaultRole = roleRepository.findByAuthority("ROLE_USER");
        user.getRoles().add(defaultRole);

        userRepository.save(user);

        return user;
    }

    public void copyDtoToEntity(UserEntity entity, UserDTO dto) {
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}
