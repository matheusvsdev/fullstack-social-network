package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.UpdateUserDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateOwnUserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseUserDTO updateSelf(UpdateUserDTO dto) {
        UserEntity user = authService.authenticated();
        user.setFullName(dto.getFullName());
        user.setUsername(dto.getUsername());

        userRepository.save(user);

        return new ResponseUserDTO(user);
    }
}
