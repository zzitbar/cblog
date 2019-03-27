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
 * Date:2018/11/14
 * Time:15:24
 */
@Service
public class BaiduHotnewsService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "baidu";

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        Document doc = Jsoup.connect("http://top.baidu.com/buzz?b=1&fr=topnews")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Elements news = doc.select("a.list-title");
        List<Hotnews> hotnewsList = new ArrayList<>();
        int i = 1;
        for (Element e : news) {
            Hotnews hotnews = new Hotnews();
            Element tr = e.parent().parent();
            hotnews.setNewsRank(tr.selectFirst("td.first").select("span").html());
            hotnews.setNewsOrder(i);
            hotnews.setNewsTitle(e.html());
            hotnews.setNewsUrl(e.attr("href"));
            hotnews.setNewsPoint(tr.selectFirst("td.last>span").html());
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
        map.put("id", Constants.CATEGORY_ID.BAIDU.getValue());
        map.put("name", "百度");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "百度实时热点");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }
}