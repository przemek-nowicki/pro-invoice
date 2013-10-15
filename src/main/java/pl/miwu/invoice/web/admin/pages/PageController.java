package pl.miwu.invoice.web.admin.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.model.User;
import pl.miwu.invoice.service.PageService;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 08.10.13
 * Time: 10:40
 */

@Controller(value="AdminPageController")
@PreAuthorize("hasRole('ADMIN_ROLE')")
@RequestMapping(value = "/admin/page")
@SessionAttributes(types = Page.class)
public class PageController {

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        model.addAttribute(new Page());
        return "admin/pages/createOrUpdate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processCreationForm(@Valid Page page, BindingResult result, SessionStatus status) {
        if( result.hasErrors() ) {
            return "admin/pages/createOrUpdate";
        } else {
            pageService.createPage(page);
            status.setComplete();
            return "redirect:/admin/pages";
        }
    }

    @RequestMapping(value = "/edit/{pageId}",method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("pageId") int pageId, Model model) {
        Page page = pageService.getPageById(pageId);
        model.addAttribute(page);
        return "admin/pages/createOrUpdate";
    }

    @RequestMapping(value = "/edit/{pageId}", method = RequestMethod.POST)
    public String processUpdateForm(@ModelAttribute("page") @Valid Page page, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "admin/pages/createOrUpdate";
        } else {
            pageService.updatePage(page);
            status.setComplete();
            return "redirect:/admin/page/edit/{pageId}";
        }
    }

    @RequestMapping(value = "/delete/{pageId}", method = RequestMethod.GET)
    public String delete(@PathVariable(value="pageId") int pageId,Model model) {
        Page page = pageService.getPageById(pageId);
        if(page!=null&&page.getId()>0) {
            pageService.deletePage(page);
        }
        return "redirect:/admin/pages/";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}