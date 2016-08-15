package com.spring.web.demo.logic.exception;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String email) {
        super("There is an account with that email address: " + email);
    }
}
