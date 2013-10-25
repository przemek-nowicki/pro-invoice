package pl.miwu.invoice.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.miwu.invoice.model.invoice.Invoice;
import pl.miwu.invoice.repository.InvoiceRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private Configuration freemarkerTemplateEngine;
    @Value("#{appProperties['PDF_DIRECTORY']}")
    private String PDF_DIRECTORY;
    @Value("#{appProperties['INVOICE_CSS_DIRECTORY']}")
    private String INVOICE_CSS_DIRECTORY;


    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

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

    @Override
    public String getInvoiceTemplate(Invoice invoice) {
        String templateName = "001";
        String requestURL = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURL().toString();
        String requestURI = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI();
        String contextPath = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getContextPath();
        requestURL=requestURL.replace(requestURI,"")+contextPath+INVOICE_CSS_DIRECTORY+templateName+".css";
        Map<String,Object> templateVars = new HashMap<String,Object>();
        templateVars.put("invoice", invoice);
        templateVars.put("cssURL",requestURL);
        try {
            freemarkerTemplateEngine.setDefaultEncoding("UTF-8");
            return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplateEngine.getTemplate(templateName+".ftl"), templateVars);
        } catch (IOException e) {
            LOGGER.error(NestedExceptionUtils.buildMessage("IO Exception occurred during creation of invoice template!",e));
            return null;
        } catch (TemplateException e) {
            LOGGER.error(NestedExceptionUtils.buildMessage("Template Exception occurred during creation of template ! ",e));
            return null;
        }
    }

    @Override
    public String generatePdf(Invoice invoice) {
        try {
            String pdfFileName = invoice.getId()+"invoice.pdf";
            Document document = new Document();
            File file = new File(PDF_DIRECTORY);
            file.mkdirs();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_DIRECTORY+pdfFileName));
            document.open();
            String html = getInvoiceTemplate(invoice);
            InputStream stream = new ByteArrayInputStream(html.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, stream);
            document.close();
            return pdfFileName;
        } catch (IOException io) {
            LOGGER.error(NestedExceptionUtils.buildMessage("IO Exception occurred during the pdf file generation!",io));
            return null;
        } catch (DocumentException de) {
            LOGGER.error(NestedExceptionUtils.buildMessage("DocumentException Exception occurred during the pdf file generation!",de));
            de.printStackTrace();
            return null;
        }
    }
}