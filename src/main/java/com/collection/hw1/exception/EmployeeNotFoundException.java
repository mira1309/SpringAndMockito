package com.collection.hw1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class EmployeeNotFoundException extends HttpStatusCodeException {

    public EmployeeNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
