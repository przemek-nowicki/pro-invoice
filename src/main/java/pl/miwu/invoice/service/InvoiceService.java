package pl.miwu.invoice.service;

import pl.miwu.invoice.model.invoice.Invoice;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:12
 */

public interface InvoiceService {
    public Invoice getInvoiceById(int id);
    public void createInvoice(Invoice invoice);
    public void updateInvoice(Invoice invoice);
    public void deleteInvoice(Invoice invoice);
    public String getInvoiceTemplate(Invoice invoice);
    public String generatePdf(Invoice invoice);
}
