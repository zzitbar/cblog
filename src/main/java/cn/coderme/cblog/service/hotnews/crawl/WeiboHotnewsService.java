package cn.coderme.cblog.service.hotnews.crawl;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.entity.hotnews.Hotnews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By zhangtengfei
 * Date:2018/11/14
 * Time:11:32
 */
@Service
public class WeiboHotnewsService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "weibo";
    private static final String WEIBO_URL = "https://s.weibo.com";

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        Document doc = Jsoup.connect("https://s.weibo.com/top/summary?cate=realtimehot")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Elements news = doc.select("#pl_top_realtimehot>table>tbody>tr");
        List<Hotnews> hotnewsList = new ArrayList<>();
        int i = 1;
        for (Element e : news) {
            Hotnews hotnews = new Hotnews();
            String rank = e.select("td.td-01").html();
            hotnews.setNewsRank(rank);
            hotnews.setNewsOrder(i);
            Element link = e.selectFirst("td.td-02>a");
            hotnews.setNewsTitle(link.html());
            hotnews.setNewsUrl(WEIBO_URL + (StringUtils.hasText(link.attr("href_to")) ? link.attr("href_to") : link.attr("href")));
            hotnews.setNewsPoint(null == e.selectFirst("td.td-02>span") ? null : e.selectFirst("td.td-02>span").html());
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
        map.put("id", Constants.CATEGORY_ID.WEIBO.getValue());
        map.put("name", "微博");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "微博热搜榜");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }

    public static void main(String[] args) {
        try {
            new WeiboHotnewsService().crawlNews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}