package pl.miwu.invoice.service;

import org.springframework.stereotype.Service;
import pl.miwu.invoice.model.Client;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 15.10.13
 * Time: 16:21
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public Collection<Client> getClients() {
        Client client = new Client();
        client.setId(1);
        client.setName("Make It With Us");
        Collection<Client> clients = new ArrayList<Client>();
        clients.add(client);
        return clients;
    }

    @Override
    public Client getClientById(int id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Make It With Us");
        return client;
    }
}
