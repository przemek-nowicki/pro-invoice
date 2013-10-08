package pl.miwu.invoice.web.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.service.PageService;

import java.util.Collection;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 08.10.13
 * Time: 09:53
 */

public class FrontendController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PageService pageService;

    @ModelAttribute("pages")
    public Collection<Page> pages(){
        return pageService.getPages();
    }

    @ModelAttribute("headTitle")
    public String headTitle(Locale locale) {
        return messageSource.getMessage("pub.includes.head.headTitle",null,locale);
    }

    @ModelAttribute("headDescription")
    public String headDescription(Locale locale) {
        return messageSource.getMessage("pub.includes.head.headDescription",null,locale);
    }

    @ModelAttribute("headKeywords")
    public String headKeywords(Locale locale) {
        return messageSource.getMessage("pub.includes.head.headKeywords",null,locale);
    }
}
