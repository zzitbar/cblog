package cn.coderme.cblog.service;

import cn.coderme.cblog.dao.ArticleTagsDAO;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.ArticleTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/23
 * Time:11:24
 */
@Service
public class ArticleTagsService {

    @Autowired
    private ArticleTagsDAO articleTagsDAO;

    public List<ArticleTags> findAll() {
        return articleTagsDAO.findAll();
    }

    /**
     * 引用最多的前10标签
     * @return
     */
    @Cacheable(value="articleTags",key="'refMax'")
    public List<ArticleTags> findRefOrder() {
        PageDto dto = new PageDto();
        dto.setRows(20);
        dto.getOrderByMap().put("tagReferenceCount", PageDto.DESC);
        Page<ArticleTags> articles = articleTagsDAO.findAll(dto.getPageable());
        return articles.getContent();
    }
}