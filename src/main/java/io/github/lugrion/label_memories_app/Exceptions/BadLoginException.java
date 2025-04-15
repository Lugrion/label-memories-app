package io.github.lugrion.label_memories_app.Exceptions;

public class BadLoginException extends RuntimeException {
    public BadLoginException(final String message) {

        super(message);
    }
}