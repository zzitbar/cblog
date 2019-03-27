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
 * 今日头条热搜 https://www.toutiao.com/2/wap/search/extra/hot_word_list/?from=toutiao_pc&is_new_ui=1
 * Created By zzitbar
 * Date:2018/11/19
 * Time:8:55
 */
@Service
public class ToutiaoHotnewsService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "toutiao";

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        Document doc = Jsoup.connect("https://www.toutiao.com/2/wap/search/extra/hot_word_list/?from=toutiao_pc&is_new_ui=1")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        List<Hotnews> hotnewsList = new ArrayList<>();
        Integer i = 1;
        Elements news = doc.select("div.list>div.card");
        for (Element e : news) {
            Hotnews hotnews = new Hotnews();
            hotnews.setNewsRank(e.selectFirst("li.card-serial-number").html());
            hotnews.setNewsOrder(i);
            Element link = e.selectFirst("a.card-content");
            hotnews.setNewsTitle(link.selectFirst("span").html());
            hotnews.setNewsUrl(link.attr("href"));
            hotnews.setNewsAuthor(e.child(1).selectFirst("a").html());
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
        map.put("id", Constants.CATEGORY_ID.TOUTIAO.getValue());
        map.put("name", "今日头条");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "今日头条热搜");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }
}