package com.matheusdev.backendjava.controller;


import com.matheusdev.backendjava.config.JwtUtil;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Verifica se o usuário existe e se a senha está correta
        if (!userService.checkPassword(username, password)) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }

        // Gera um token JWT
        String token = jwtUtil.generateToken(username);

        return ResponseEntity.ok(token);
    }
}
