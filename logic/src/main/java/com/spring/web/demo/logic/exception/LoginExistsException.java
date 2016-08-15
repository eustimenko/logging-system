package com.spring.web.demo.logic.exception;

public class LoginExistsException extends RuntimeException {

    public LoginExistsException(String login) {
        super("There is an account with that login: " + login);
    }
}
