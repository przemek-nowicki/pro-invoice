package pl.miwu.invoice.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    public void testCreateInvoice() {

    }
}
