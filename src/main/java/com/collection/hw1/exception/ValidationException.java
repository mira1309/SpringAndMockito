package com.collection.hw1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends HttpStatusCodeException {
    public ValidationException (String massage) {
        super(HttpStatus.BAD_REQUEST,massage);
    }
}
