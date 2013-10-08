package pl.miwu.invoice.service;

import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.model.UserRole;
import pl.miwu.invoice.model.User;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:36
 */
public interface UserService {
    public Collection<User> getUsers();
    public User getUserById(int id);
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public Collection<UserRole> getUserRoles();
    public void createUserRole(UserRole userRole);
    public UserRole getUserRoleByName(String name);
    public UserRole getUserRoleById(int id);
}
