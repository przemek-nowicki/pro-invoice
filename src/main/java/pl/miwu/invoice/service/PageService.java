package pl.miwu.invoice.service;

import pl.miwu.invoice.model.Page;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 07.10.13
 * Time: 15:01
 */
public interface PageService {
    public Collection<Page> getPages();
    public Page getPageById(int id);
    public Page getPageByUrl(String url);
    public void createPage(Page page);
    public void updatePage(Page page);
    public void deletePage(Page page);
}
