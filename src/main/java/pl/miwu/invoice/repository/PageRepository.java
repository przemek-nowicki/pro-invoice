package pl.miwu.invoice.repository;

import pl.miwu.invoice.model.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:33
 */
public interface PageRepository {
    public List<Page> getAll();
    public Page getById(int id);
    public Page getByUrl(String url);
    public void create(Page page);
    public void update(Page page);
    public void delete(Page page);
}
