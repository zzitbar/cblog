package cn.coderme.cblog.service.hotnews.crawl;

import cn.coderme.cblog.dao.hotnews.HotnewsDAO;
import cn.coderme.cblog.entity.hotnews.Hotnews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created By zzitbar
 * Date:2018/11/14
 * Time:13:51
 */
public abstract class HotnewsCrawlService {

    public static Map<String, Map> CATEGORY_MAP = new HashMap();

    @Autowired
    private HotnewsDAO hotnewsDAO;

    @Transactional
    public void updateNews() throws Exception {
        List<Hotnews> hotnewsList = crawlNews();
        if (null != hotnewsList && hotnewsList.size() > 0) {
            hotnewsDAO.deleteByNewsCategoryAndNewsDate(hotnewsList.get(0).getNewsCategory(), LocalDate.now());
            hotnewsList.stream().forEach(e -> {
                hotnewsDAO.save(e);
            });
        }
    }

    public abstract List<Hotnews> crawlNews() throws Exception;

    public List<Hotnews> listByCategory(String category, LocalDate date) {
        return hotnewsDAO.findByNewsCategoryAndNewsDateOrderByNewsOrderAsc(category, date);
    }

    public abstract void addCategoryList();

    public static List<Map> getCategoryList() {
        return CATEGORY_MAP.values().stream().sorted(Comparator.comparingInt(o -> (Integer) o.get("id"))).collect(Collectors.toList());
    }

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

    public static String replaceHTMLTagsText(String text) {
        return text.replace("\n", "").replaceAll("<\\/?[\\s\\S]+?\\/?>", "");
    }
}