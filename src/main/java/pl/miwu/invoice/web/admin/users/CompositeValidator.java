package pl.miwu.invoice.web.admin.users;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 27.09.13
 * Time: 10:24
 */
@Component
public class CompositeValidator implements Validator {

    private Validator[] validators;

    /**
     * Will return true if this class is in the specified map.
     */
    public boolean supports(final Class clazz) {
        for (Validator v : validators) {
            if (v.supports(clazz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate the specified object using the validator registered for the
     * object's class.
     */
    public void validate(final Object obj, final Errors errors) {

        for (Validator v : validators) {
            if (v.supports(obj.getClass())) {
                v.validate(obj, errors);
            }
        }
    }

    public void setValidators(Validator[] validators) {
        this.validators = validators;
    }
}


