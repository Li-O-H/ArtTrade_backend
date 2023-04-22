package com.itmo.ArtTrade.controller.validation;

import com.itmo.ArtTrade.controller.payload.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

    @Override
    public void initialize(PasswordMatching constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationRequest request = (RegistrationRequest) obj;
        return request.getPassword().equals(request.getMatchingPassword());
    }
}