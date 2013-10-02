package pl.miwu.invoice.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 16.09.13
 * Time: 10:40
 */

@Transactional
public class HibernateRepository {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
