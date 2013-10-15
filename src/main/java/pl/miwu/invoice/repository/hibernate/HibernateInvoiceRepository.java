package pl.miwu.invoice.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.Invoice;
import pl.miwu.invoice.repository.InvoiceRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 15.10.13
 * Time: 19:48
 */

@Repository
@Transactional
public class HibernateInvoiceRepository extends HibernateRepository implements InvoiceRepository {

    @Override
    public Invoice getById(int id) {
        return (Invoice)getCurrentSession().get(Invoice.class,id);
    }

    @Override
    public void create(Invoice invoice) {
        getCurrentSession().save(invoice);
        getCurrentSession().flush();
        getCurrentSession().refresh(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        getCurrentSession().merge(invoice);
        getCurrentSession().flush();
    }

    @Override
    public void delete(Invoice invoice) {
        getCurrentSession().delete(invoice);
        getCurrentSession().flush();
    }
}
