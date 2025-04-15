package io.github.lugrion.label_memories_app.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    private final String jwt;
    private final String status; 
}