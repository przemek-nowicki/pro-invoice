package pl.miwu.invoice.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;
import pl.miwu.invoice.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * This class resolve problem of using JSR-303 and Traditional Bean Validation.
 * By default the WebDataBinder allows to set just one Validator.
 * It's possible to use custom Validator (like UserValidator) or the JSR-303 validation but not both at the the same time.
 * The main idea is to join the results of JSR303 based on annotations and custom Validator that inherits from this class.
 * A nice blog entry about this topic http://blog.jteam.nl/2009/08/04/bean-validation-integrating-jsr-303-with-spring/
 *
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 27.09.13
 * Time: 19:54
 */

public abstract class BaseValidator implements org.springframework.validation.Validator, InitializingBean {
    protected Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    public abstract void doValidate(Object target, Errors errors);

    @Override
    public abstract boolean supports(Class clazz);

    @Override
    public final void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath,"", message);
        }
        doValidate(target,errors);
    }
}
