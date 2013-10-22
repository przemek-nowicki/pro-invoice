package pl.miwu.invoice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.miwu.invoice.model.invoice.Invoice;
import pl.miwu.invoice.model.invoice.InvoiceItem;
import pl.miwu.invoice.model.invoice.Item;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 15.10.13
 * Time: 21:29
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context/spring-mvc.xml", "classpath:context/hibernate-orm.xml", "classpath:context/spring-security.xml"})
public class InvoiceServiceTests {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ClientService clientService;

    @Test
    public void testCreateInvoice() throws ParseException {
        Invoice invoice = new Invoice();
        invoice.setClient(clientService.getClientById(1));
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-14");
        invoice.setDate(d);
                invoice.setNo("Test invoice");
        InvoiceItem ii = new InvoiceItem();
        Item i = new Item();
        i.setName("Test items");
        i.setAmount(new BigDecimal(111));
        ii.setItem(i);
        ii.setPosition(2);
        invoice.getInvoiceItems().add(ii);
        invoiceService.createInvoice(invoice);
    }

    //@Test
    public void comaprisionList() {
        List<InvoiceItem> list = invoiceService.getInvoiceById(47).getInvoiceItems();
        List<InvoiceItem> list2 = invoiceService.getInvoiceById(47).getInvoiceItems();
        InvoiceItem needle = list2.get(0);
        if(list.contains(needle)==true){
            System.out.print("i found!");
        }  else {
            System.out.print("sorry");
        }
    }
    //@Test
    public void testGetInvoice() {
       Invoice invoice = invoiceService.getInvoiceById(14);
       List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
       for(InvoiceItem ii : invoiceItems ) {
           ii.getItem();
       }
    }


}
