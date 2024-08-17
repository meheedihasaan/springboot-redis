package com.meheedihasaan.springbootredis.controllers;

import com.meheedihasaan.springbootredis.models.User;
import com.meheedihasaan.springbootredis.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user) {
        UUID id = userService.create(user);
        URI location = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/generate-token")
    public ResponseEntity<String> generateUserToken(@PathVariable("id") UUID id) {
        String token = userService.generateUserToken(id);
        if (token == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        return ResponseEntity.ok(userService.generateUserToken(id));
    }

    @GetMapping(value = "/email/get")
    public ResponseEntity<String> getEmailByUserToken(@RequestParam(name = "token") String token) {
        return ResponseEntity.ok(userService.getEmailByUserToken(token));
    }
}
