package pl.miwu.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.UserRole;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.repository.RoleRepository;
import pl.miwu.invoice.repository.UserRepository;
import pl.miwu.invoice.service.auth.UserDetailsAdapter;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:36
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SaltSource saltSource;

    @Override
    @Transactional(readOnly = true)
    public Collection<User> getUsers() {
        return userRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.create(user);
        String password = user.getPassword();
        Object salt = saltSource.getSalt(new UserDetailsAdapter(user));
        user.setPassword(passwordEncoder.encodePassword(password,salt));
        userRepository.update(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UserRole> getUserRoles() {
        return roleRepository.getAll();
    }

    @Override
    @Transactional
    public void createUserRole(UserRole userRole) {
        roleRepository.create(userRole);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole getUserRoleByName(String name) {
        return roleRepository.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole getUserRoleById(int id) {
        return roleRepository.getById(id);
    }
}
