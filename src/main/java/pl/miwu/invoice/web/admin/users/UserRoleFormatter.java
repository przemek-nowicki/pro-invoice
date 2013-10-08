package pl.miwu.invoice.web.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import pl.miwu.invoice.model.UserRole;
import pl.miwu.invoice.service.UserService;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 26.09.13
 * Time: 12:07
 */
public class UserRoleFormatter implements Formatter<UserRole> {

    @Autowired
    private UserService userService;

    @Override
    public String print(UserRole userRole, Locale locale) {
        return userRole.toString();
    }

    @Override
    public UserRole parse(String s, Locale locale) throws ParseException {
        Integer roleId = Integer.parseInt(s);
        UserRole userRole = userService.getUserRoleById(roleId);
        if(userRole.getId().equals(roleId)) {
            return userRole;
        }
        throw new ParseException("Role not found: "+s, 0);
    }
}
