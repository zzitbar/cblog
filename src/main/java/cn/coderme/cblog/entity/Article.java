package cn.coderme.cblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:15:35
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String articleTitle;
    private String articleAbstract;
    private String articleTags;
    private String articleAuthorEmail;
    private Integer articleCommentCount = 0;
    private Integer articleViewCount = 0;
    private String articleContent;
    private Date articleCreateDate;
    private Date articleUpdateDate = new Date();
    private Integer articleCommentable;
    private String articleViewPwd;

    private Long articleCategoryId;

    private Integer enabled = 1;// 是否可见

    private Integer hot = 0;//热度

    @Transient
    private String articleCategoryName;

    @ManyToOne(cascade={CascadeType.ALL,CascadeType.REFRESH})
    @JoinColumn(name="articleCategoryId", referencedColumnName="id", insertable=false, updatable=false)
    @JsonIgnore
    private ArticleCategory articleCategory;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = ArticleTags.class, cascade = CascadeType.ALL)
    @JoinTable(name = "t_tag_article",
            joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private List<ArticleTags> articleTagsList;

    @Transient
    private List<String> tags;

    public Article() {
    }

    public Article(Long id, String articleAbstract, String articleTags, String articleTitle,
                   Date articleUpdateDate, Date articleCreateDate, Integer articleCommentCount, Integer articleViewCount, String categoryName) {
        this.id=id;
        this.articleAbstract=articleAbstract;
        this.articleTags=articleTags;
        this.articleTitle=articleTitle;
        this.articleUpdateDate=articleUpdateDate;
        this.articleCreateDate=articleCreateDate;
        this.articleCategoryName=categoryName;
        this.articleCommentCount=articleCommentCount;
        this.articleViewCount=articleViewCount;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public String getArticleAuthorEmail() {
        return articleAuthorEmail;
    }

    public void setArticleAuthorEmail(String articleAuthorEmail) {
        this.articleAuthorEmail = articleAuthorEmail;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getArticleCreateDate() {
        return articleCreateDate;
    }

    public void setArticleCreateDate(Date articleCreateDate) {
        this.articleCreateDate = articleCreateDate;
    }

    public Date getArticleUpdateDate() {
        return articleUpdateDate;
    }

    public void setArticleUpdateDate(Date articleUpdateDate) {
        this.articleUpdateDate = articleUpdateDate;
    }

    public Integer getArticleCommentable() {
        return articleCommentable;
    }

    public void setArticleCommentable(Integer articleCommentable) {
        this.articleCommentable = articleCommentable;
    }

    public String getArticleViewPwd() {
        return articleViewPwd;
    }

    public void setArticleViewPwd(String articleViewPwd) {
        this.articleViewPwd = articleViewPwd;
    }

    public Long getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public ArticleCategory getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticleCategory articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleCategoryName() {
        return articleCategoryName;
    }

    public void setArticleCategoryName(String articleCategoryName) {
        this.articleCategoryName = articleCategoryName;
    }

    public List<ArticleTags> getArticleTagsList() {
        return articleTagsList;
    }

    public void setArticleTagsList(List<ArticleTags> articleTagsList) {
        this.articleTagsList = articleTagsList;
    }

    public List<String> getTags() {
        if (StringUtils.hasText(this.articleTags)) {
            return Arrays.asList(this.articleTags.split(","));
        } else {
            return tags;
        }
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * 计算热度
     */
    public void calculateHot() {
        this.setHot(this.getArticleCommentCount()*2 + this.getArticleViewCount());
    }
}

