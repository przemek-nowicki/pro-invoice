package pl.miwu.invoice.web.admin.invoices;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.invoice.Invoice;
import pl.miwu.invoice.service.ClientService;
import pl.miwu.invoice.service.InvoiceService;
import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private Configuration freemarkerTemplateEngine;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        Invoice invoice = new Invoice();
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

    @RequestMapping(value = "/preview/{invoiceId}",method = RequestMethod.GET)
    public String preview(@PathVariable("invoiceId") int invoiceId, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        model.addAttribute(invoice);
        return "admin/invoices/preview";
    }

    @RequestMapping(value = "/download/{invoiceId}",method = RequestMethod.GET)
    public ModelAndView download(@PathVariable("invoiceId") int invoiceId, ModelAndView modelAndView, Locale locale) throws IOException, TemplateException {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        modelAndView.addObject(invoice);
        modelAndView.setViewName("admin/invoices/preview");
        Map templateVars = new HashMap();
        freemarkerTemplateEngine.setDefaultEncoding("UTF-8");
        templateVars.put("invoice",invoice);
        String str = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplateEngine.getTemplate("001.ftl"), templateVars);
        modelAndView.addObject("invoiceView",str);
        return modelAndView;
    }

    @ModelAttribute("clients")
    public Collection<Client> clients(){
        return clientService.getClients();
    }

    @ModelAttribute("symbols")
    public Collection<CurrencySymbol> currencySymbols(){
        return Arrays.asList(CurrencySymbol.values());
    }

    @ModelAttribute("codes")
    public Collection<CurrencyCode> currencyCodes() {
        return Arrays.asList(CurrencyCode.values());
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
