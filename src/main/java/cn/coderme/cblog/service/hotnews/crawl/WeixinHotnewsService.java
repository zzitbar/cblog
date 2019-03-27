package cn.coderme.cblog.service.hotnews.crawl;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.entity.hotnews.Hotnews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By zzitbar
 * Date:2018/11/16
 * Time:15:19
 */
@Service
public class WeixinHotnewsService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "weixin";

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        Document doc = Jsoup.connect("https://data.wxb.com/rankArticle")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        List<Hotnews> hotnewsList = new ArrayList<>();
        Integer i = 1;
        Elements news = doc.select("div.ant-table-body>table>tbody>tr");
        for (Element e : news) {
            Hotnews hotnews = new Hotnews();
            hotnews.setNewsRank(i.toString());
            hotnews.setNewsOrder(i);
            Element link = e.child(0).selectFirst("div.article-title>a.title-text");
            hotnews.setNewsTitle(link.html());
            hotnews.setNewsUrl(link.attr("href"));
            hotnews.setNewsAuthor(e.child(1).selectFirst("a").html());
            hotnews.setNewsPoint("<i class='iconfont icon-view'></i> " + replaceHTMLTagsText(e.child(3).html()) + " <i class='iconfont icon-praise'></i> " + replaceHTMLTagsText(e.child(4).html()));
            hotnews.setNewsCategory(NEWS_CATEGORY);
            hotnews.setNewsDate(LocalDate.now());
            hotnews.setCreateTime(LocalDateTime.now());
            hotnews.setUpdateTime(LocalDateTime.now());
            hotnews.setOriginData(e.outerHtml());
            hotnewsList.add(hotnews);
            i++;
        }
        return hotnewsList;
    }

    @Override
    @PostConstruct
    public void addCategoryList() {
        Map map = new HashMap();
        map.put("id", Constants.CATEGORY_ID.WEIXIN.getValue());
        map.put("name", "微信");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "微信热门文章");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }

    public static void main(String[] args) throws Exception {
        new WeixinHotnewsService().crawlNews();
//        System.out.println(new WeixinHotnewsService().replaceUnusedText("<!-- react-text: 200 -->10万<!-- /react-text -->"));
    }
}