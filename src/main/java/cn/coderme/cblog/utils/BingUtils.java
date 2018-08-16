package cn.coderme.cblog.utils;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.base.QiniuKey;
import cn.coderme.cblog.dto.bing.BingCnDto;
import cn.coderme.cblog.dto.qiniu.FetchRetDto;
import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.service.bing.BingImageArchiveService;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 微软bing壁纸
 * Created By zhangtengfei
 * Date:2018/4/25
 * Time:11:34
 */
@Component
public class BingUtils {

    private static final String SITE_URL = "http://www.istartedsomething.com/bingimages/?";
    private static final String IMAGE_BASE_URL = "http://www.istartedsomething.com/bingimages/cache/";

    private static final String BING_CN_URL = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";

    public static final String BASE_DIR = "/bing-image";

    @Autowired
    private QiniuKey qiniuKey;
    @Autowired
    private QiniuUtils qiniuUtils;
    @Autowired
    private BingImageArchiveService bingImageArchiveService;

    public void dealImage(BingImageArchive image) {
        if (!StringUtils.startsWithIgnoreCase(image.getImageUrl(), "http")) {
            image.setImageUrl(qiniuKey.getQiniuUrl()+"/"+image.getImageUrl());
        }
    }

    public List<BingImageArchive> downloadImage(Integer year, Integer month, Integer day) throws IOException {
        Document doc = Jsoup.connect(SITE_URL+"y="+year+"&m="+month)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Elements elements = null;
        if (null != day) {
            Elements dayElement = doc.select("span.day:matches(^"+day.toString()+"$)");
            if (null != dayElement) {
                elements = new Elements();
                for (Element e : dayElement) {
                    elements.add(e.parent());
                }
            }
        } else {
            elements = doc.select("td.dated");
        }
        List<BingImageArchive> result = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        if (null != elements) {
            for (Element e : elements) {
                Elements links = e.select("a");
                if (null != links) {
                    for (Element link : links) {
                        String href = link.attr("href");//#20091002-au
                        String zone = link.attr("class");
                        String title = link.attr("title");//White egrets perch on a live oak tree in Bayou Grand Caillou, Dulac, Louisiana -- Philip Gould/Corbis © (Bing Australia)
                        Elements image = link.select("img.lazy");
                        String imgfile = image.attr("data-original");
                        String alt = image.attr("alt").replace(",", "，");
                        String[] alts2 = alt.split("\\(©", 2);
                        String[] alts1 = alts2[0].split("，", 2);

                        BingImageArchive imageArchive = new BingImageArchive();
                        imageArchive = new BingImageArchive();
                        imageArchive.setImageAlt(image.attr("alt"));
                        imageArchive.setImageDate(href.substring(1, 9));
                        imageArchive.setImageTitle(alts1[0]);
                        imageArchive.setImagePlace(alts1.length>=2?alts1[1]:null);
                        imageArchive.setImageProvider(alts2.length>=2?"(@"+alts2[1]:null);
                        imageArchive.setImageZone(zone);
                        imageArchive.setOriginUrl(IMAGE_BASE_URL+imgfile.replace("resize.php?i=", "").replace("&w=100", ""));

                        LocalDate imageDate = LocalDate.parse(imageArchive.getImageDate(), formatter);
                        imageArchive.setImageDateEnd(imageDate.plusDays(1).format(DateTimeFormatter.ISO_DATE));
                        imageArchive.setImageUrl(BASE_DIR+"/"+imageArchive.getImageZone()+"/"+imageArchive.getImageDate());
                        FetchRetDto fetchRetDto = qiniuUtils.fetctToUpload(imageArchive.getOriginUrl(), imageArchive.getImageUrl());
                        result.add(imageArchive);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 从 bing API 获取每日图片
     * https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
     */
    public void downloadImage() {
        RestTemplate restTemplate = new RestTemplate();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        ResponseEntity<String> response = restTemplate.getForEntity(BING_CN_URL, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            BingCnDto bingCnDto = new Gson().fromJson(response.getBody(), BingCnDto.class);
            if (null != bingCnDto && null != bingCnDto.getImages() && bingCnDto.getImages().size()>0) {
                bingCnDto.getImages().stream().forEach(x -> {
                    LocalDate date = LocalDate.parse(x.getStartdate(), dtf);
                    BingImageArchive imageArchive = new BingImageArchive();

                    String imageTitle = x.getCopyright();
                    String[] alts2 = imageTitle.split("\\(©", 2);
                    String[] alts1 = alts2[0].split("，", 2);

                    imageArchive.setImageTitle(alts1[0]);
                    imageArchive.setImagePlace(alts1.length>=2?alts1[1]:null);
                    imageArchive.setImageProvider(alts2.length>=2?"(@"+alts2[1]:null);

                    imageArchive.setOriginUrl("http://s.cn.bing.net"+x.getUrl());
                    imageArchive.setImageDate(x.getStartdate());
                    imageArchive.setImageDateEnd(date.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    imageArchive.setImageAlt(x.getCopyright());
                    imageArchive.setImageZone(Constants.IMAGE_ZONE.CN.getValue());

                    imageArchive.setCopyrightlink(x.getCopyrightlink());
                    imageArchive.setCreateTime(new Date());

                    imageArchive.setImageUrl(BingUtils.BASE_DIR+"/"+imageArchive.getImageZone()+"/"+imageArchive.getImageDate());
                    FetchRetDto fetchRetDto = qiniuUtils.fetctToUpload(imageArchive.getOriginUrl(), imageArchive.getImageUrl());

                    bingImageArchiveService.save(imageArchive);
                });
            }
        }
    }


    public static void main(String[] args) {
        BingUtils bingUtils = new BingUtils();
        try {
            bingUtils.downloadImage(2018, 5, 9);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}