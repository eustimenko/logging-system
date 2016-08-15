package com.spring.web.demo.api.exception;

import com.spring.web.demo.logic.exception.*;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException e) {
        logger.error("Controller occurs an exception", e);
    }

    @ExceptionHandler(value = EmailExistsException.class)
    @ResponseStatus(value = HttpStatus.IM_USED)
    public void handleEmailExistsException(EmailExistsException e) {
        logger.error("Controller occurs an exception", e);
    }

    @ExceptionHandler(value = LoginExistsException.class)
    @ResponseStatus(value = HttpStatus.IM_USED)
    public void handleLoginExistsException(LoginExistsException e) {
        logger.error("Controller occurs an exception", e);
    }
}
