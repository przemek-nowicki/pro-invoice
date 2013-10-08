package pl.miwu.invoice.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.repository.PageRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 08.10.13
 * Time: 08:33
 */

@Repository
@Transactional
public class HibernatePageRepository extends HibernateRepository implements PageRepository {
    @Override
    public List<Page> getAll() {
        return (List<Page>)getCurrentSession().createCriteria(Page.class).list();
    }

    @Override
    public Page getById(int id) {
        return (Page)getCurrentSession().get(Page.class,id);
    }

    @Override
    public Page getByUrl(String url) {
        return (Page)getCurrentSession().getNamedQuery(Page.GET_BY_URL).setString("url",url).setMaxResults(1).uniqueResult();
    }

    @Override
    public void create(Page page) {
        getCurrentSession().save(page);
        getCurrentSession().flush();
        getCurrentSession().refresh(page);
    }

    @Override
    public void update(Page page) {
        getCurrentSession().merge(page);
        getCurrentSession().flush();
    }

    @Override
    public void delete(Page page) {
        getCurrentSession().delete(page);
        getCurrentSession().flush();
    }
}
