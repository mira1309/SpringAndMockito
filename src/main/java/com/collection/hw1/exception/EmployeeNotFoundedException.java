package com.collection.hw1.exception;

public class EmployeeNotFoundedException extends RuntimeException{
    public EmployeeNotFoundedException (String message) {
        super(message);
    }
}
