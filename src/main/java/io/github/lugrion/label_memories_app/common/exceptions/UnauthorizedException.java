package io.github.lugrion.label_memories_app.common.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(final String message) {
        super(message);
    }
}