package pl.miwu.invoice.web.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.service.UserService;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 18.09.13
 * Time: 13:01
 */

@Controller
@PreAuthorize("hasRole('ADMIN_ROLE')")
@RequestMapping(value="/admin/users",method= RequestMethod.GET)
public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="",method = RequestMethod.GET)
    public String list() {
        return "admin/users/list";
    }

    @ModelAttribute("users")
    public Collection<User> users(){
        return userService.getUsers();
    }
}
