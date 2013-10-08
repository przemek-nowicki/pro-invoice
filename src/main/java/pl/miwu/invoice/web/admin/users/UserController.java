package pl.miwu.invoice.web.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.miwu.invoice.model.UserRole;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.service.UserService;

import javax.validation.Valid;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 21.09.13
 * Time: 09:09
 */

@Controller(value="AdminUserController")
@PreAuthorize("hasRole('ADMIN_ROLE')")
@RequestMapping(value = "/admin/user")
@SessionAttributes(types = User.class)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        model.addAttribute(new User());
        return "admin/users/createOrUpdate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processCreationForm(@Valid User user, BindingResult result, SessionStatus status) {
        if( result.hasErrors() ) {
            return "admin/users/createOrUpdate";
        } else {
            userService.createUser(user);
            status.setComplete();
            return "redirect:/admin/users";
        }
    }

    @RequestMapping(value = "/edit/{userId}",method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("userId") int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute(user);
        return "admin/users/createOrUpdate";
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
    public String processUpdateForm(@ModelAttribute("user") @Valid User user, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "admin/users/createOrUpdate";
        } else {
            userService.updateUser(user);
            status.setComplete();
            return "redirect:/admin/user/edit/{userId}";
        }
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable(value="userId") int userId,Model model) {
        User user = userService.getUserById(userId);
        if(user!=null&&user.getId()>0) {
            userService.deleteUser(user);
        }
        return "redirect:/admin/users/";
    }

    @ModelAttribute("roles")
    public Collection<UserRole> roles(){
        return userService.getUserRoles();
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(userValidator);
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
