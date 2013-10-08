package pl.miwu.invoice.web.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.service.UserService;
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
    private UserService userService;

    @Override
    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void doValidate(Object target, Errors errors) {
        User user = (User)target;
        User userDs = userService.getUserByUsername(user.getUsername());
        if(userDs!=null&&userDs.getUsername().equals(user.getUsername())) {
            if((user.getId()==null)||(user.getId()!=null&&!user.getId().equals(userDs.getId()))) {
                errors.rejectValue("username","UserValidator.username.duplicate");
            }
        }
        userDs = userService.getUserByEmail(user.getEmail());
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

