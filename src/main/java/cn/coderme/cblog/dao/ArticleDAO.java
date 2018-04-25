package cn.coderme.cblog.dao;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:15:39
 */
public interface ArticleDAO extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Query(value = "select new Article(a.id, a.articleAbstract, a.articleTags, a.articleTitle, " +
            "a.articleUpdateDate, a.articleCreateDate, a.articleCommentCount, a.articleViewCount, ac.categoryName) " +
            "from Article a left join a.articleCategory ac ")
    Page<Article> findAllJoin(Pageable pageable);
}