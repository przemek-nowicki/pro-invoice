package pl.miwu.invoice.repository.hibernate;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.invoice.Invoice;
import pl.miwu.invoice.model.invoice.InvoiceItem;
import pl.miwu.invoice.model.invoice.Item;
import pl.miwu.invoice.repository.InvoiceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
        List<InvoiceItem> invoiceItemList = invoice.getInvoiceItems();
        invoice.setInvoiceItems(null);
        getCurrentSession().save(invoice);
        for(InvoiceItem invoiceItem : invoiceItemList ) {
            invoiceItem.setInvoice(invoice);
            getCurrentSession().save(invoiceItem.getItem());
            getCurrentSession().flush();
            getCurrentSession().save(invoiceItem);
            getCurrentSession().flush();
        }
    }

    @Override
    public void update(Invoice invoice) {
        Session session = getCurrentSession();
        for(Iterator<InvoiceItem> iterator = invoice.getInvoiceItems().iterator();iterator.hasNext();)   {
            InvoiceItem invoiceItem = iterator.next();
            if(invoiceItem.isRemoved()) {
                iterator.remove();
                session.delete(invoiceItem.getItem());
                session.flush();
            } else {
                Item item = invoiceItem.getItem();
                session.saveOrUpdate(item);
                session.flush();
                invoiceItem.setInvoice(invoice);
                session.saveOrUpdate(invoiceItem);
                session.flush();
            }
        }
        session.saveOrUpdate(invoice);
        session.flush();
    }

    @Override
    public void delete(Invoice invoice) {
        getCurrentSession().delete(invoice);
        getCurrentSession().flush();
    }
}
