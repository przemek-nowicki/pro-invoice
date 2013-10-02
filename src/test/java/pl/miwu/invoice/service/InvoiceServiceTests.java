package pl.miwu.invoice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.model.UserRole;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 02.10.13
 * Time: 15:47
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context/spring-mvc.xml", "classpath:context/hibernate-orm.xml", "classpath:context/spring-security.xml"})
public class InvoiceServiceTests {
    @Autowired
    private InvoiceService invoiceService;

    @Test
    @Transactional
    public void testCreateUser() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@make-it-with.us");
        user.setPassword("y7i$dwA_");
        user.setEnabled(true);
        String roleName = "ADMIN_ROLE";
        UserRole userRole = invoiceService.getRoleByName(roleName);
        if(userRole ==null) {
            userRole = new UserRole();
            userRole.setName(roleName);
            invoiceService.createRole(userRole);
        }
        user.setUserRole(userRole);
        invoiceService.createUser(user);
        User foundUser = invoiceService.getUserById(user.getId());
        assertEquals(user.getUsername(),foundUser.getUsername());
        assertEquals(user.getUserRole().getName(),foundUser.getUserRole().getName());
    }

    @Test
    @Transactional
    public void getUsers() {
        Collection<User> users = invoiceService.getUsers();
        int usersListSize = users.size();
        User user = getMockUser();
        invoiceService.createUser(user);
        Collection<User> users2 = invoiceService.getUsers();
        assertEquals(usersListSize+1, users2.size());
    }

    @Test
    @Transactional
    public void getUser() {
        User user = getMockUser();
        invoiceService.createUser(user);
        User userByUsername = invoiceService.getUserByUsername(user.getUsername());
        User userByEmail = invoiceService.getUserByEmail(user.getEmail());

        assertEquals(userByUsername.getUsername(),user.getUsername());
        assertEquals(userByEmail.getEmail(),user.getEmail());
    }

    private User getMockUser() {
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@make-it-with.us");
        user.setPassword("#ff0000");
        user.setEnabled(true);
        String roleName = "USER_ROLE";
        UserRole userRole = invoiceService.getRoleByName(roleName);
        if(userRole ==null) {
            userRole = new UserRole();
            userRole.setName(roleName);
            invoiceService.createRole(userRole);
        }
        user.setUserRole(userRole);
        return user;
    }

}
