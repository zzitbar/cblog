package cn.coderme.cblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/20
 * Time:11:53
 */
@Entity
@Table(name = "t_tags")
public class ArticleTags implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer tagPublishedRefCount = 0;
    private Integer tagReferenceCount = 0;
    private String tagTitle;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Article.class, cascade = CascadeType.ALL)
    @JoinTable(name = "t_tag_article",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTagPublishedRefCount() {
        return tagPublishedRefCount;
    }

    public void setTagPublishedRefCount(Integer tagPublishedRefCount) {
        this.tagPublishedRefCount = tagPublishedRefCount;
    }

    public Integer getTagReferenceCount() {
        return tagReferenceCount;
    }

    public void setTagReferenceCount(Integer tagReferenceCount) {
        this.tagReferenceCount = tagReferenceCount;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}