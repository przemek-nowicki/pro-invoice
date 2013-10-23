package pl.miwu.invoice.service;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 09:37
 */

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import pl.miwu.invoice.model.invoice.Invoice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context/spring-mvc.xml", "classpath:context/hibernate-orm.xml", "classpath:context/spring-security.xml"})
public class MakeInvoiceTests {
    @Autowired
    private Configuration freemarkerTemplateEngine;
    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testInvoiceCreation() throws IOException, DocumentException, TemplateException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
        document.open();
        Map templateVars = new HashMap();
        Invoice invoice = invoiceService.getInvoiceById(48);
        freemarkerTemplateEngine.setDefaultEncoding("UTF-8");
        templateVars.put("invoice",invoice);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplateEngine.getTemplate("001.ftl"), templateVars);
        InputStream stream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, stream);
        //step 5
        document.close();

        System.out.println( "PDF Created!" );
    }

}
