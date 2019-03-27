package cn.coderme.cblog.entity.hotnews;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 热点新闻
 * Created By zzitbar
 * Date:2018/11/14
 * Time:11:24
 */
@Entity
@Table(name = "hot_news")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Hotnews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String newsRank;//
    private String newsTitle;//标题
    private String newsUrl;//链接
    private String newsAbstract;//摘要
    private String newsPoint;//热点指数
    private String newsCategory;//分类
    private LocalDate newsDate;//日期
    private Integer newsOrder;//序号
    private String originData;// 原始数据
    private String newsAuthor;// 作者
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsRank() {
        return newsRank;
    }

    public void setNewsRank(String newsRank) {
        this.newsRank = newsRank;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public String getNewsPoint() {
        return newsPoint;
    }

    public void setNewsPoint(String newsPoint) {
        this.newsPoint = newsPoint;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDate getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(LocalDate newsDate) {
        this.newsDate = newsDate;
    }

    public Integer getNewsOrder() {
        return newsOrder;
    }

    public void setNewsOrder(Integer newsOrder) {
        this.newsOrder = newsOrder;
    }

    public String getOriginData() {
        return originData;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    @Override
    public String toString() {
        return "Hotnews{" +
                "id=" + id +
                ", newsRank='" + newsRank + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                ", newsAbstract='" + newsAbstract + '\'' +
                ", newsPoint='" + newsPoint + '\'' +
                ", newsCategory='" + newsCategory + '\'' +
                ", newsDate=" + newsDate +
                ", newsOrder=" + newsOrder +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}