package pl.miwu.invoice.repository;

import pl.miwu.invoice.model.invoice.Invoice;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 15.10.13
 * Time: 19:51
 */
public interface InvoiceRepository {
    public Invoice getById(int id);
    public void create(Invoice invoice);
    public void update(Invoice invoice);
    public void delete(Invoice invoice);
}
