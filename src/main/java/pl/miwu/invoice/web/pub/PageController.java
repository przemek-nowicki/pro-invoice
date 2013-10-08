package pl.miwu.invoice.web.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.service.PageService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 08.10.13
 * Time: 08:44
 */

@Controller
@RequestMapping("/page/{page}")
public class PageController extends FrontendController {

    @Autowired
    private PageService pageService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewPage(@PathVariable("page") Page page){
        return "pub/page";
    }

    @InitBinder("page")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Page.class,new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) {
                setValue(pageService.getPageByUrl(text));
            }
        });
    }

    @ModelAttribute("headTitle")
    public String headTitle(@PathVariable("page") Page page) {
        return page.getTitle();
    }

    @ModelAttribute("headDescription")
    public String headDescription(@PathVariable("page") Page page) {
        return page.getMetaDescription();
    }

    @ModelAttribute("headKeywords")
    public String headKeywords(@PathVariable("page") Page page) {
        return page.getMetaKeywords();
    }
}