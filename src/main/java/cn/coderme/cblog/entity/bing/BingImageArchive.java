package cn.coderme.cblog.entity.bing;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created By zzitbar
 * Date:2018/4/28
 * Time:11:36
 */
@Entity
@Table(name = "bing_image_archive")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BingImageArchive implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageDate;//日期
    private String imageTitle;//图片标题
    private String imagePlace;//地点
    private String imageProvider;//版权
    private String imageZone;//区域，bing中国、bing美国等
    private String imageUrl;//七牛文件名
    private String imageAlt;
    private String originUrl;//原始url

    private String imageDateEnd;//图片显示截至日期

    private String copyrightlink;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImagePlace() {
        return imagePlace;
    }

    public void setImagePlace(String imagePlace) {
        this.imagePlace = imagePlace;
    }

    public String getImageProvider() {
        return imageProvider;
    }

    public void setImageProvider(String imageProvider) {
        this.imageProvider = imageProvider;
    }

    public String getImageZone() {
        return imageZone;
    }

    public void setImageZone(String imageZone) {
        this.imageZone = imageZone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getImageDateEnd() {
        return imageDateEnd;
    }

    public void setImageDateEnd(String imageDateEnd) {
        this.imageDateEnd = imageDateEnd;
    }

    public String getCopyrightlink() {
        return copyrightlink;
    }

    public void setCopyrightlink(String copyrightlink) {
        this.copyrightlink = copyrightlink;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}