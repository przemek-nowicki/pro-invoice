package pl.miwu.invoice.service;

import org.springframework.stereotype.Service;
import pl.miwu.invoice.model.Client;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:02
 */

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Override
    public Collection<Client> getClients() {
        Client client = new Client();
        client.setName("Make It With Us");
        Collection<Client> clients = new ArrayList<Client>();
        clients.add(client);
        return clients;
    }
}
