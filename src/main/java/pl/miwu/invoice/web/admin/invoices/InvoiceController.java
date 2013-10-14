package pl.miwu.invoice.web.admin.invoices;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.Invoice;
import pl.miwu.invoice.service.InvoiceService;


import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 10:58
 */

@Controller(value="AdminInvoiceController")
@PreAuthorize("hasRole('ADMIN_ROLE')")
@RequestMapping(value = "/admin/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        model.addAttribute(new Invoice());
        return "admin/invoices/createOrUpdate";
    }


    @ModelAttribute("clients")
    public Collection<Client> clients(){
        return invoiceService.getClients();
    }
}
