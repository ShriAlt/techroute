package com.xworkz.techroute_userservice.exception;

public class EmailNotExistException extends RuntimeException {
    public EmailNotExistException(String message) {
        super(message);
    }
}
