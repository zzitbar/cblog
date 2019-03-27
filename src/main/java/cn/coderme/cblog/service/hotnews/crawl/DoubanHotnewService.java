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
 * 豆瓣热门话题 https://www.douban.com/gallery/
 * Created By zzitbar
 * Date:2018/11/16
 * Time:14:44
 */
@Service
public class DoubanHotnewService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "douban";

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        Document doc = Jsoup.connect("https://www.douban.com/gallery/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        List<Hotnews> hotnewsList = new ArrayList<>();
        Integer i = 1;
        Elements news = doc.select("ul.trend>li");
        for (Element e : news) {
            Hotnews hotnews = new Hotnews();
            hotnews.setNewsRank(i.toString());
            hotnews.setNewsOrder(i);
            Element link = e.selectFirst("a");
            hotnews.setNewsTitle(link.html());
            hotnews.setNewsUrl(link.attr("href"));
            hotnews.setNewsPoint(null == e.selectFirst("span") ? null : e.selectFirst("span").html());
            hotnews.setNewsCategory(NEWS_CATEGORY);
            hotnews.setNewsDate(LocalDate.now());
            hotnews.setCreateTime(LocalDateTime.now());
            hotnews.setUpdateTime(LocalDateTime.now());
            hotnewsList.add(hotnews);
            i++;
        }
        return hotnewsList;
    }

    @Override
    @PostConstruct
    public void addCategoryList() {
        Map map = new HashMap();
        map.put("id", Constants.CATEGORY_ID.DOUBAN.getValue());
        map.put("name", "豆瓣");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "豆瓣24小时话题趋势");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }
}