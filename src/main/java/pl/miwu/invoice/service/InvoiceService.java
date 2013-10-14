package pl.miwu.invoice.service;

import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.InvoiceItem;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:12
 */

public interface InvoiceService {
    public Collection<Client> getClients();
    public Collection<InvoiceItem> getItems();

}
