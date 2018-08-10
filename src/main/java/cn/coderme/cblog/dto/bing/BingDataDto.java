package cn.coderme.cblog.dto.bing;

/**
 * http://bing.plmeizi.com/getpic
 * Created By zzitbar
 * Date:2018/8/10
 * Time:14:57
 */
public class BingDataDto {

    /**
     * thumb : http://bimgs.plmeizi.com/images/bing/2018/SmithsonianBones_ZH-CN10706349393_1920x1080.jpg-thumb
     * image : http://bimgs.plmeizi.com/images/bing/2018/SmithsonianBones_ZH-CN10706349393_1920x1080.jpg
     * big : http://bimgs.plmeizi.com/images/bing/2018/SmithsonianBones_ZH-CN10706349393_1920x1080.jpg
     * title : 关于博物馆的千百种幻想 1846年8月10日成立的史密森尼学会 (© Mark Kauffman/Getty Images)
     * description : 今天的这张照片拍摄于1953年，它描绘了史密森尼学会作为一个任何人都可以去了解美国以及其以外的世界的地方继续发展所需要的奉献精神。史密森尼学会的启动资金来源于英国科学家詹姆斯·史密森价值50.8万美元的遗赠捐款，1846年的今天，詹姆斯·诺克斯·波尔克总统签署了建立史密森尼学会的立法。
     * link :
     * layer :
     * id : 805
     * date : 20180810
     * searchlink : http://www.bing.com/search?q=%E5%8F%B2%E5%AF%86%E6%A3%AE%E5%B0%BC%E5%AD%A6%E4%BC%9A&form=hpcapt&mkt=zh-cn
     */

    private String thumb;
    private String image;
    private String big;
    private String title;
    private String description;
    private String link;
    private String layer;
    private String id;
    private String date;
    private String searchlink;
    private String reltit;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearchlink() {
        return searchlink;
    }

    public void setSearchlink(String searchlink) {
        this.searchlink = searchlink;
    }

    public String getReltit() {
        return reltit;
    }

    public void setReltit(String reltit) {
        this.reltit = reltit;
    }
}