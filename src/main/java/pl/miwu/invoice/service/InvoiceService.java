package pl.miwu.invoice.service;

import pl.miwu.invoice.model.Invoice;
import pl.miwu.invoice.model.Item;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:12
 */

public interface InvoiceService {
    public Collection<Item> getItems();
    public void createInvoice(Invoice invoice);
    public void updateInvoice(Invoice invoice);
    public void deleteInvoice(Invoice invoice);
}
