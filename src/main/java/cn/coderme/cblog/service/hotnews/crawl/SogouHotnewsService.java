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
 * 搜狗热点 http://top.sogou.com/hot/shishi_1.html
 * Created By zzitbar
 * Date:2018/11/16
 * Time:11:31
 */
@Service
public class SogouHotnewsService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "sogou";

    private static final Integer SOUGOU_PAGES = 3;

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        List<Hotnews> hotnewsList = new ArrayList<>();
        Integer i = 1;
        for (Integer page = 1; page <= SOUGOU_PAGES; page++) {
            Document doc = Jsoup.connect("http://top.sogou.com/hot/shishi_" + page + ".html")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                    .timeout(60000)
                    .get();
            Elements news = doc.select("ul.pub-list>li");
            for (Element e : news) {
                Hotnews hotnews = new Hotnews();
                hotnews.setNewsRank(i.toString());
                hotnews.setNewsOrder(i);

                if (e.hasClass("oneline")) {
                    // 第三行以后
                    Element link = e.selectFirst("span.s2>p.p3>a");
                    hotnews.setNewsTitle(link.html());
                    hotnews.setNewsUrl(link.attr("href"));
                } else {
                    // 前三行
                    Element link = e.selectFirst("span.s2>p.p1>a");
                    hotnews.setNewsTitle(link.html());
                    hotnews.setNewsUrl(link.attr("href"));
                    hotnews.setNewsAbstract(e.selectFirst("span.s2>p.p2").html());
                }
                if (null != e.selectFirst("span.s3")) {
                    e.selectFirst("span.s3>i").remove();
                    hotnews.setNewsPoint(e.selectFirst("span.s3").html());
                }

                hotnews.setNewsCategory(NEWS_CATEGORY);
                hotnews.setNewsDate(LocalDate.now());
                hotnews.setCreateTime(LocalDateTime.now());
                hotnews.setUpdateTime(LocalDateTime.now());
                hotnewsList.add(hotnews);
                i++;
            }
        }
        return hotnewsList;
    }

    @Override
    @PostConstruct
    public void addCategoryList() {
        Map map = new HashMap();
        map.put("id", Constants.CATEGORY_ID.SOGOU.getValue());
        map.put("name", "搜狗");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "搜狗实时热点");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }
}