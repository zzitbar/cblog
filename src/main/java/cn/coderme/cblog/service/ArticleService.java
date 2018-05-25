package cn.coderme.cblog.service;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dao.ArticleCategoryDAO;
import cn.coderme.cblog.dao.ArticleDAO;
import cn.coderme.cblog.dao.ArticleTagsDAO;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.Article;
import cn.coderme.cblog.entity.ArticleCategory;
import cn.coderme.cblog.entity.ArticleTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:15:46
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private ArticleCategoryDAO articleCategoryDAO;
    @Autowired
    private ArticleTagsDAO articleTagsDAO;

    /**
     * 分页查询
     * @param dto
     * @return
     */
    public PageBean<Article> page(PageDto dto) {
//        articleDAO.findAll(new Specification<Article>() {
//            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Join<Article, ArticleCategory> abMap = root.join("articleCategory",
//                        JoinType.LEFT);
//                List<Predicate> lstPredicates = new ArrayList<Predicate>();
//                Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
//                return cb.and(lstPredicates.toArray(arrayPredicates));
//            }
//        }, dto.getPageable());
//        Page<Article> articles = articleDAO.findAllJoin(dto.getPageable());
        return new PageBean<Article>(articleDAO.findAllJoin(dto.getPageable()));
    }

    public Article getById(Long id) {
        return articleDAO.findOne(id);
    }

    /**
     * 增加浏览量
     * @param article
     */
    @CacheEvict(value="article",key="'hotest'")
    @Transactional
    public void read(Article article) {
        article.setArticleViewCount((null==article.getArticleViewCount()?0:article.getArticleViewCount())+1);
        article.calculateHot();
        articleDAO.save(article);
    }

    /**
     * 评论文章
     * TODO
     */
    @CacheEvict(value="article",key="'hotest'")
    @Transactional
    public void comment() {

    }

    /**
     * 保存文章
     * @param article
     */
    @Caching(evict = {@CacheEvict(value="article",key="'lastest'"), @CacheEvict(value="article",key="'hotest'"),
            @CacheEvict(value="articleTags",key="'refMax'")})
    @Transactional
    public void update(Article article) {
        if (null == article.getId()) {
            // 处理文章分类
            dealCategory(article);
            // 处理标签
            dealTags(article);
            articleDAO.save(article);
        } else {
            Article ex = articleDAO.getOne(article.getId());
            ex.setArticleTitle(article.getArticleTitle());
            ex.setArticleAbstract(article.getArticleAbstract());
            ex.setArticleContent(article.getArticleContent());
            ex.setArticleCategoryId(article.getArticleCategoryId());
            ex.setArticleTags(article.getArticleTags());
            ex.setArticleCreateDate(article.getArticleCreateDate());
            ex.setArticleUpdateDate(new Date());
            // 处理文章分类
            dealCategory(ex);
            // 处理标签
            dealTags(ex);

            articleDAO.save(ex);
        }
    }

    private void dealCategory(Article article) {
        ArticleCategory articleCategory = article.getArticleCategory();
        if (null != articleCategory) {
            if (!articleCategory.getId().equals(article.getArticleCategoryId())) {
                articleCategory.setArticleCount(articleCategory.getArticleCount()-1);
                articleCategoryDAO.save(articleCategory);

                ArticleCategory category = articleCategoryDAO.findOne(article.getArticleCategoryId());
                category.setArticleCount(category.getArticleCount()+1);
                article.setArticleCategory(category);
            }
        } else if (null != article.getArticleCategoryId()) {
            ArticleCategory category = articleCategoryDAO.getOne(article.getArticleCategoryId());
            category.setArticleCount(category.getArticleCount()+1);
            article.setArticleCategory(category);
        }
    }
    /**
     * 处理标签
     * @param article
     */
    private void dealTags(Article article) {
        List<String> oldTags = new ArrayList<>();
        if (null != article.getArticleTagsList()) {
            for (ArticleTags articleTags : article.getArticleTagsList()) {
                oldTags.add(articleTags.getTagTitle());
            }
        }

        article.setArticleTagsList(new ArrayList<>());
        List<String> tags = StringUtils.hasText(article.getArticleTags())?Arrays.asList(article.getArticleTags().split(",")):new ArrayList<>();
        for (String tag : tags) {
            ArticleTags articleTags = articleTagsDAO.findByTagTitle(tag);
            if (null == articleTags) {
                articleTags = new ArticleTags();
                articleTags.setTagTitle(tag);
                articleTags.setTagPublishedRefCount(1);
                articleTags.setTagReferenceCount(1);
                articleTags.setArticles(new ArrayList<>());
//                    articleTags.getArticles().add(ex);
//                    articleTagsDAO.save(articleTags);
            } else if (!oldTags.contains(tag)) {
                articleTags.setTagReferenceCount(articleTags.getTagReferenceCount()+1);
                articleTags.setTagPublishedRefCount(articleTags.getTagPublishedRefCount()+1);
            }
            article.getArticleTagsList().add(articleTags);
        }
    }

    /**
     * 获取最热的十条文章
     * @return
     */
    @Cacheable(value="article",key="'hotest'")
    public List<Article> findHotest() {
        PageDto dto = new PageDto();
        dto.getOrderByMap().put("hot", PageDto.DESC);
        Page<Article> articles = articleDAO.findAll(dto.getPageable());
        return articles.getContent();
    }

    /**
     * 获取最新十条文章
     * @return
     */
    @Cacheable(value="article",key="'lastest'")
    public List<Article> findLastest() {
        PageDto dto = new PageDto();
        dto.getOrderByMap().put("articleCreateDate", PageDto.DESC);
        Page<Article> articles = articleDAO.findAll(dto.getPageable());
        return articles.getContent();
    }

    @Caching(evict = {@CacheEvict(value="article",key="'lastest'"), @CacheEvict(value="article",key="'hotest'"),
            @CacheEvict(value="articleTags",key="'refMax'")})
    @Transactional
    public void delete(Long id) {
        Article article = articleDAO.getOne(id);
        // 处理文章分类
        ArticleCategory articleCategory = article.getArticleCategory();
        articleCategory.setArticleCount(articleCategory.getArticleCount()-1);
        articleCategoryDAO.save(articleCategory);
        // 标签
        List<ArticleTags> articleTags = article.getArticleTagsList();
        if (null != articleTags) {
            for (ArticleTags tag : articleTags) {
                tag.setTagReferenceCount(tag.getTagReferenceCount()-1);
                tag.setTagPublishedRefCount(tag.getTagPublishedRefCount()-1);
                articleTagsDAO.save(tag);
            }
        }
        articleDAO.delete(id);
    }
}