package io.github.lugrion.label_memories_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lugrion.label_memories_app.dto.LoginResponse;
import io.github.lugrion.label_memories_app.dto.Requests.LoginRequest;
import io.github.lugrion.label_memories_app.dto.Requests.RegisterRequest;
import io.github.lugrion.label_memories_app.Security.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    // Register Response?
    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody RegisterRequest payload) {
        return ResponseEntity.ok(authService.registerAccount(payload));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAccount(@RequestBody LoginRequest payload) {
        return ResponseEntity.ok(authService.loginAccount(payload));
    }

}
