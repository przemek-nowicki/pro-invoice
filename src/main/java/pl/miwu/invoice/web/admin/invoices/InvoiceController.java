package pl.miwu.invoice.web.admin.invoices;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.model.invoice.Invoice;
import pl.miwu.invoice.service.ClientService;
import pl.miwu.invoice.service.InvoiceService;
import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    @Value("#{appProperties['PDF_DIRECTORY']}")
    private String PDF_DIRECTORY;

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
        model.addAttribute("invoiceTemplate",invoiceService.getInvoiceTemplate(invoice));
        return "admin/invoices/preview";
    }

    @RequestMapping(value = "/download/{invoiceId}",method = RequestMethod.GET)
    public void download(@PathVariable("invoiceId") int invoiceId, HttpServletResponse response) throws IOException, TemplateException {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        String pdfFileName = invoiceService.generatePdf(invoice);

        File downloadFile = new File(PDF_DIRECTORY+pdfFileName);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        response.setContentType("application/pdf");
        response.setContentLength((int)downloadFile.length());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", downloadFile.getName()));

        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
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
