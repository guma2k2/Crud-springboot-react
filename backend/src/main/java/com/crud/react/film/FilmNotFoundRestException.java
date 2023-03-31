package com.crud.react.film;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmNotFoundRestException extends Exception {

    public FilmNotFoundRestException(String message) {
        super(message);
    }
}
