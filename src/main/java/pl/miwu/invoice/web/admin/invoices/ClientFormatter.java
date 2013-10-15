package pl.miwu.invoice.web.admin.invoices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.UserRole;
import pl.miwu.invoice.service.ClientService;
import pl.miwu.invoice.service.InvoiceService;
import pl.miwu.invoice.service.UserService;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 26.09.13
 * Time: 12:07
 */

public class ClientFormatter implements Formatter<Client> {

    @Autowired
    private ClientService clientService;

    @Override
    public String print(Client client, Locale locale) {
        return client.toString();
    }

    @Override
    public Client parse(String s, Locale locale) throws ParseException {
        Integer clientId = Integer.parseInt(s);
        Client client = clientService.getClientById(clientId);
        if(client.getId().equals(clientId)) {
            return client;
        }
        throw new ParseException("Client not found: "+s, 0);
    }
}
