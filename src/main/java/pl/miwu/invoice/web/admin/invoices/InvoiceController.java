package pl.miwu.invoice.web.admin.invoices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.Invoice;
import pl.miwu.invoice.service.ClientService;
import pl.miwu.invoice.service.InvoiceService;
import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;

import javax.validation.Valid;
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
@SessionAttributes(types = Invoice.class)
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        Invoice invoice = new Invoice();
        //invoice.setItems(new AutoPopulatingList(Invoice.class));
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

    @RequestMapping(value = "/edit/{invoiceId}",method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("invoiceId") int invoiceId, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        model.addAttribute(invoice);
        return "admin/invoices/createOrUpdate";
    }

    @RequestMapping(value = "/edit/{invoiceId}", method = RequestMethod.POST)
    public String processUpdateForm(@ModelAttribute("invoice") @Valid Invoice invoice, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "admin/invoices/createOrUpdate";
        } else {
            invoiceService.updateInvoice(invoice);
            status.setComplete();
            return "redirect:/admin/invoice/edit/{invoiceId}";
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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
