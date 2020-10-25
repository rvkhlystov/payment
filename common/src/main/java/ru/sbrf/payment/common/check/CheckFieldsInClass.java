package ru.sbrf.payment.common.check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CheckFieldsInClass {

    private static Validator validator;

    public static <T extends Object> T validate(T t) throws BusinessExceptions {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0) {
            throw new BusinessExceptions(constraintViolations.iterator().next().getMessage());
        }
        else return t;
    }
}
