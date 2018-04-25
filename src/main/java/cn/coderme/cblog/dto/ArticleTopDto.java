package cn.coderme.cblog.dto;

import cn.coderme.cblog.entity.Article;
import cn.coderme.cblog.entity.ArticleTags;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/24
 * Time:14:36
 */
public class ArticleTopDto {

    private List<Article> hotest;
    private List<Article> lastest;
    private List<ArticleTags> tags;

    public List<Article> getHotest() {
        return hotest;
    }

    public void setHotest(List<Article> hotest) {
        this.hotest = hotest;
    }

    public List<Article> getLastest() {
        return lastest;
    }

    public void setLastest(List<Article> lastest) {
        this.lastest = lastest;
    }

    public List<ArticleTags> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTags> tags) {
        this.tags = tags;
    }
}