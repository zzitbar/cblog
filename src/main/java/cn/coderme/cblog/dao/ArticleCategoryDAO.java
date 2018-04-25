package cn.coderme.cblog.dao;

import cn.coderme.cblog.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By zhangtengfei
 * Date:2018/4/20
 * Time:10:21
 */
public interface ArticleCategoryDAO extends JpaRepository<ArticleCategory, Long> {
}