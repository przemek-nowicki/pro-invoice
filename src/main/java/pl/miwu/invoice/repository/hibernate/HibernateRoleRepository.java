package pl.miwu.invoice.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.UserRole;
import pl.miwu.invoice.repository.RoleRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 16.09.13
 * Time: 23:05
 */

@Repository
@Transactional
public class HibernateRoleRepository extends HibernateRepository implements RoleRepository {

    @Override
    public List<UserRole> getAll() {
        return (List<UserRole>)getCurrentSession().createCriteria(UserRole.class).list();
    }

    @Override
    public UserRole getById(int id) {
        return (UserRole)getCurrentSession().get(UserRole.class,id);
    }

    @Override
    public UserRole getByName(String name) {
        return (UserRole)getCurrentSession().getNamedQuery(UserRole.GET_BY_NAME).setString("name",name).setMaxResults(1).uniqueResult();
    }

    @Override
    public void create(UserRole userRole) {
        getCurrentSession().save(userRole);
        getCurrentSession().flush();
        getCurrentSession().refresh(userRole);
    }
}
