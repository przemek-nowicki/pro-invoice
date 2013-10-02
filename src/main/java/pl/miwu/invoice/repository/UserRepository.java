package pl.miwu.invoice.repository;

import pl.miwu.invoice.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:34
 */
public interface UserRepository {
    public List<User> getAll();
    public User getById(int id);
    public User getByUsername(String username);
    public User getByEmail(String email);
    public void create(User user);
    public void update(User user);
    public void delete(User user);
}
