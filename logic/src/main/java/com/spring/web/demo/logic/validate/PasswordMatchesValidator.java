package com.spring.web.demo.logic.validate;

import javax.validation.*;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    public void initialize(PasswordMatches constraint) {
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        return false;
    }
}
