package io.github.lugrion.label_memories_app.common.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.lugrion.label_memories_app.common.response.GeneralResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(LogicException.class)
    public GeneralResponse handleBadRequest(final LogicException ex) {
        return new GeneralResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public GeneralResponse handleNotFound(final EntityNotFoundException ex) {
        return new GeneralResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public GeneralResponse handleUnauthorized(final UnauthorizedException ex) {
        return new GeneralResponse(ex.getMessage());
    }
}
