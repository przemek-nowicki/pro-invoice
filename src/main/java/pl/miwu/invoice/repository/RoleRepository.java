package pl.miwu.invoice.repository;

import pl.miwu.invoice.model.UserRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:33
 */
public interface RoleRepository {
    public List<UserRole> getAll();
    public UserRole getById(int id);
    public UserRole getByName(String name);
    public void create(UserRole userRole);
}
