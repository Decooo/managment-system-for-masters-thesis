package com.jakub.validator;

import com.jakub.model.Tematy;
import com.jakub.model.Wiadomosci;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Jakub on 01.06.2017.
 */
@Component
public class TematyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Tematy.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tematy tematy = (Tematy) target;
        if (tematy.getTemat().length() < 10) {
            errors.rejectValue("temat", "SmallSize.topicForm.temat");
        }
    }
}
