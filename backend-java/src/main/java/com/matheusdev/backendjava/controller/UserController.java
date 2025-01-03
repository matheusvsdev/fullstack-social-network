package com.matheusdev.backendjava.controller;

import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.UserDTO;
import com.matheusdev.backendjava.service.AuthService;
import com.matheusdev.backendjava.service.UpdateOwnUserService;
import com.matheusdev.backendjava.dto.UpdateUserDTO;
import com.matheusdev.backendjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UpdateOwnUserService updateOwnUserService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> findAll() {
        List<ResponseUserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{objectId}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable String objectId) {
        ResponseUserDTO user = userService.findById(objectId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/{objectId}")
    public ResponseEntity<ResponseUserDTO> update(@PathVariable String objectId, @RequestBody UserDTO userDTO) {
        ResponseUserDTO user = userService.update(objectId, userDTO);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "{objectId}")
    public ResponseEntity<ResponseUserDTO> delete(@PathVariable String objectId) {
        userService.delete(objectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/me")
    public ResponseEntity<ResponseUserDTO> findMe() {
        ResponseUserDTO userDTO = authService.getMe();
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(value = "/me")
    public ResponseEntity<ResponseUserDTO> updateSelf(@RequestBody UpdateUserDTO dto) {
        ResponseUserDTO newDto = updateOwnUserService.updateSelf(dto);
        return ResponseEntity.ok().body(newDto);
    }
}
