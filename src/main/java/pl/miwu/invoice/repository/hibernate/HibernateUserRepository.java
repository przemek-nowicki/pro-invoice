package pl.miwu.invoice.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.repository.UserRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:34
 */

@Repository
@Transactional
public class HibernateUserRepository extends HibernateRepository implements UserRepository {

    @Override
    public List<User> getAll() {
        return (List<User>)getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public User getById(int id) {
        return (User)getCurrentSession().get(User.class,id);
    }

    @Override
    public User getByUsername(String username) {
       return (User)getCurrentSession().getNamedQuery(User.GET_BY_USERNAME).setString("username",username).setMaxResults(1).uniqueResult();
    }

    @Override
    public User getByEmail(String username) {
        return (User)getCurrentSession().getNamedQuery(User.GET_BY_EMAIL).setString("email",username).setMaxResults(1).uniqueResult();
    }

    @Override
    public void create(User user) {
        getCurrentSession().save(user);
        getCurrentSession().flush();
        getCurrentSession().refresh(user);
    }

    @Override
    public void update(User user) {
        getCurrentSession().merge(user);
        getCurrentSession().flush();
    }

    @Override
    public void delete(User user) {
        getCurrentSession().delete(user);
        getCurrentSession().flush();
    }
}
