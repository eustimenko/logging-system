package com.spring.web.demo.api.exception;

import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = NoSuchElementException.class)
    public void handleNoSuchElementException(NoSuchElementException e) {
        logger.error("Controller occurs an exception", e);
    }
}
