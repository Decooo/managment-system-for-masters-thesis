package com.jakub.validator;

import com.jakub.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Jakub on 26.05.2017.
 */
@Component
public class UsersValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Users.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users users = (Users) target;
        //ValidationUtils.rejectIfEmpty(errors, "login", "NotEmpty.registrationForm.login");
        //ValidationUtils.rejectIfEmpty(errors, "haslo", "NotEmpty.registrationForm.haslo");
        ValidationUtils.rejectIfEmpty(errors, "imie", "NotEmpty.registrationForm.imie");
        ValidationUtils.rejectIfEmpty(errors, "nazwisko", "NotEmpty.registrationForm.nazwisko");

        if (users.getLogin().length() < 3) {
            errors.rejectValue("login", "SmallSize.registrationForm.login");
        }
        if (users.getHaslo().length() < 3) {
            errors.rejectValue("haslo", "SmallSize.registrationForm.haslo");
        }
    }
}
