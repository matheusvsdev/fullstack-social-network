package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseUserDTO> insertUser(@RequestBody UserDTO userDTO) {
        ResponseUserDTO user = userService.insert(userDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> findAll() {
        List<ResponseUserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable String id) {
        ResponseUserDTO user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        ResponseUserDTO user = userService.update(id, userDTO);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ResponseUserDTO> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
