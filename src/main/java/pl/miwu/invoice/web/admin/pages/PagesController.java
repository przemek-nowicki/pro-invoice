package pl.miwu.invoice.web.admin.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.service.PageService;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 07.10.13
 * Time: 14:37
 */

@Controller(value = "AdminPagesController")
@PreAuthorize("hasRole('ADMIN_ROLE')")
@RequestMapping(value="/admin/pages",method= RequestMethod.GET)
public class PagesController {
    @Autowired
    private PageService pageService;

    @RequestMapping(value="",method = RequestMethod.GET)
    public String list() {
        return "admin/pages/list";
    }

    @ModelAttribute("pages")
    public Collection<Page> pages(){
        return pageService.getPages();
    }
}
