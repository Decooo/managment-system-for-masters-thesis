package com.jakub.validator;

import com.jakub.model.Users;
import com.jakub.model.Wiadomosci;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.xml.bind.annotation.XmlAccessorOrder;

/**
 * Created by Jakub on 30.05.2017.
 */
@Component
public class WiadomosciValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Wiadomosci.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Wiadomosci wiadomosci = (Wiadomosci) target;
        if (wiadomosci.getTemat().length() < 3) {
            errors.rejectValue("temat", "SmallSize.messageForm.temat");
        }
        if (wiadomosci.getTresc().length() < 3) {
            errors.rejectValue("tresc", "SmallSize.messageForm.tresc");
        }
    }
}
