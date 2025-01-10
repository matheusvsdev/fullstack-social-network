package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.*;
import com.matheusdev.backendjava.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/me")
    public ResponseEntity<ResponseUserProfileDTO> findMe() {
        ResponseUserProfileDTO userDTO = authService.getMe();
        return ResponseEntity.ok(userDTO);
    }
}
