package com.collection.hw1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends HttpStatusCodeException {

    public EmployeeStorageIsFullException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
