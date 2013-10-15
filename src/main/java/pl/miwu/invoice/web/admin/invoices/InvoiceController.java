package pl.miwu.invoice.web.admin.invoices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.Invoice;
import pl.miwu.invoice.model.Item;
import pl.miwu.invoice.service.ClientService;
import pl.miwu.invoice.service.InvoiceService;
import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;


import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 10:58
 */

@Controller(value="AdminInvoiceController")
@PreAuthorize("hasRole('ADMIN_ROLE')")
@RequestMapping(value = "/admin/invoice")
@SessionAttributes(types = Invoice.class)
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        Invoice invoice = new Invoice();
        invoice.setItems((Set<Item>) invoiceService.getItems());
        model.addAttribute(invoice);
        return "admin/invoices/createOrUpdate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processCreationForm(@Valid Invoice invoice, BindingResult result, SessionStatus status) {
        if( result.hasErrors() ) {
            return "admin/invoices/createOrUpdate";
        } else {
            invoiceService.createInvoice(invoice);
            status.setComplete();
            return "redirect:/admin/invoices";
        }
    }
    @ModelAttribute("clients")
    public Collection<Client> clients(){
        return clientService.getClients();
    }

    @ModelAttribute("symbols")
    public CurrencySymbol[] currencySymbols(){
        return CurrencySymbol.values();
    }

    @ModelAttribute("codes")
    public CurrencyCode[] currencyCodes() {
        return CurrencyCode.values();
    }
}
