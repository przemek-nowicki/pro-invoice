package pl.miwu.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.miwu.invoice.model.Page;
import pl.miwu.invoice.repository.PageRepository;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 07.10.13
 * Time: 14:59
 */

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Page> getPages() {
        return pageRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page getPageById(int id) {
        return pageRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page getPageByUrl(String url) {
        return pageRepository.getByUrl(url);
    }

    @Override
    @Transactional
    public void createPage(Page page) {
        pageRepository.create(page);
    }

    @Override
    @Transactional
    public void updatePage(Page page) {
        pageRepository.update(page);
    }

    @Override
    @Transactional
    public void deletePage(Page page) {
        pageRepository.delete(page);
    }
}
