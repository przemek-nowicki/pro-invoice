package pl.miwu.invoice.service;

import pl.miwu.invoice.model.Client;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 15.10.13
 * Time: 16:17
 */
public interface ClientService {
    public Collection<Client> getClients();
    public Client getClientById(int id);
}
