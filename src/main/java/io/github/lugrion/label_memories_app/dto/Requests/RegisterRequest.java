package io.github.lugrion.label_memories_app.dto.Requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class RegisterRequest {
    private final String email;
    private final String username;
    private final String password;
}
