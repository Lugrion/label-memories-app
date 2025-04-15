package io.github.lugrion.label_memories_app.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.lugrion.label_memories_app.dto.GeneralResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(LogicException.class)
    public GeneralResponse handleBadRequest(final LogicException ex) {
        return new GeneralResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailNotFoundException.class)
    public GeneralResponse handleEmailNotFound(final EmailNotFoundException ex) {
        return new GeneralResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadLoginException.class)
    public GeneralResponse handleBadLogin(final BadLoginException ex) {
        return new GeneralResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDataNotFoundException.class)
    public GeneralResponse handleUserDataNotFound(final UserDataNotFoundException ex) {
        return new GeneralResponse(ex.getMessage());
    }
}
