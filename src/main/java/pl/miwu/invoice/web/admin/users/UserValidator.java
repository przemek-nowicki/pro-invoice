package pl.miwu.invoice.web.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.service.InvoiceService;
import pl.miwu.invoice.util.BaseValidator;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 27.09.13
 * Time: 10:03
 */

@Component
public class UserValidator extends BaseValidator {

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void doValidate(Object target, Errors errors) {
        User user = (User)target;
        User userDs = invoiceService.getUserByUsername(user.getUsername());
        if(userDs!=null&&userDs.getUsername().equals(user.getUsername())) {
            if((user.getId()==null)||(user.getId()!=null&&!user.getId().equals(userDs.getId()))) {
                errors.rejectValue("username","UserValidator.username.duplicate");
            }
        }
        userDs = invoiceService.getUserByEmail(user.getEmail());
        if(userDs!=null&&userDs.getEmail().equals(user.getEmail())) {
            if((user.getId()==null)||(user.getId()!=null&&!user.getId().equals(userDs.getId()))) {
                errors.rejectValue("email","UserValidator.email.duplicate");
            }
        }
        if(user.getUserRole()==null){
            errors.rejectValue("userRole","UserValidator.userRole.notEmpty");
        }
    }
}

