package pl.miwu.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.miwu.invoice.model.invoice.Invoice;
import pl.miwu.invoice.repository.InvoiceRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:02
 */

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice getInvoiceById(int id) {
        return invoiceRepository.getById(id);
    }

    @Override
    public void createInvoice(Invoice invoice) {
        invoiceRepository.create(invoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.update(invoice);
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }
}
