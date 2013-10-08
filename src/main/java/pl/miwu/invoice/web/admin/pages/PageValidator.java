package pl.miwu.invoice.web.admin.pages;

import org.springframework.validation.Errors;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.util.BaseValidator;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 08.10.13
 * Time: 10:56
 */
public class PageValidator extends BaseValidator {

    @Override
    public boolean supports(Class clazz) {
        return Page.class.isAssignableFrom(clazz);
    }
    @Override
    public void doValidate(Object target, Errors errors) {
        Page page = (Page)target;
        // TODO: check if page-url already exists in database!
    }
}
