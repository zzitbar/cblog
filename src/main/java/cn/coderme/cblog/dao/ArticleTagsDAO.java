package cn.coderme.cblog.dao;

import cn.coderme.cblog.entity.ArticleTags;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By zhangtengfei
 * Date:2018/4/23
 * Time:11:23
 */
public interface ArticleTagsDAO extends JpaRepository<ArticleTags, Long> {

    ArticleTags findByTagTitle(String tagTitle);
}
