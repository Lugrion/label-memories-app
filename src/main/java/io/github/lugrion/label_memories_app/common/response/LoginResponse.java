package io.github.lugrion.label_memories_app.common.response;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long expiresIn
) {
}