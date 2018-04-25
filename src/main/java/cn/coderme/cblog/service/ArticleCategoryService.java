package cn.coderme.cblog.service;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dao.ArticleCategoryDAO;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.ArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/20
 * Time:10:21
 */
@Service
public class ArticleCategoryService {

    @Autowired
    private ArticleCategoryDAO articleCategoryDAO;

    /**
     * 分页查询
     * @param dto
     * @return
     */
    public PageBean<ArticleCategory> page(PageDto dto) {
        return new PageBean<ArticleCategory>(articleCategoryDAO.findAll(dto.getPageable()));
    }

    public ArticleCategory getById(Long id) {
        return articleCategoryDAO.getOne(id);
    }

    public List<ArticleCategory> findAll(){
        return articleCategoryDAO.findAll();
    }

    @Transactional
    public void update(ArticleCategory category) {
        if (null == category.getId()) {
            category.setCreateTime(new Date());
            category.setUpdateTime(new Date());
            articleCategoryDAO.save(category);
        } else {
            ArticleCategory ex = articleCategoryDAO.getOne(category.getId());
            ex.setCategoryName(category.getCategoryName());
            ex.setUpdateTime(new Date());
            articleCategoryDAO.save(ex);
        }
    }
}