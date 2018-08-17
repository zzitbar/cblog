package cn.coderme.cblog.jobs;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.dto.bing.BingDataMainDto;
import cn.coderme.cblog.dto.qiniu.FetchRetDto;
import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.service.bing.BingImageArchiveService;
import cn.coderme.cblog.utils.BingUtils;
import cn.coderme.cblog.utils.QiniuUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/5/2
 * Time:14:32
 */
@Component
public class BingImageJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BingImageArchiveService bingImageArchiveService;
    @Autowired
    private BingUtils bingUtils;
    @Autowired
    private QiniuUtils qiniuUtils;

    /**
     * 每天凌晨3点触发
     */
//    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(fixedRate = 30000)
    public void everyday() {
        String date = bingImageArchiveService.maxDate();
        LocalDate maxDate = LocalDate.parse(date);
        StopWatch stopWatch = new StopWatch("获取BING壁纸定时任务");
        stopWatch.start();
        while (maxDate.isBefore(LocalDate.now())) {
            try {
                List<BingImageArchive> imageArchives = bingUtils.downloadImage(maxDate.getYear(), maxDate.getMonthValue(), maxDate.getDayOfMonth());
                if (null != imageArchives && imageArchives.size() > 0) {
                    imageArchives.stream().filter(imageArchive -> StringUtils.hasText(imageArchive.getImageUrl())).forEach(imageArchive -> bingImageArchiveService.save(imageArchive));
                }
            } catch (Exception e) {
                logger.error("下载BING图片出错", e);
            }
            maxDate = maxDate.plusDays(1);
        }
        stopWatch.stop();
        System.out.println(stopWatch.toString());
    }

    /**
     * 从 bing API 获取每日图片
     * https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void bingcn() {
        String date = bingImageArchiveService.maxDate();
        LocalDate maxDate = LocalDate.parse(date);
        StopWatch stopWatch = new StopWatch("获取BING壁纸定时任务");
        stopWatch.start();
        while (maxDate.isBefore(LocalDate.now())) {
            try {
                bingUtils.downloadImage();
            } catch (Exception e) {
                logger.error("下载BING图片出错", e);
            }
            maxDate = maxDate.plusDays(1);
        }
        stopWatch.stop();
        System.out.println(stopWatch.toString());
    }

    /**
     * 抓取网页
     * @param id
     */
    public void history(Integer id)  {
        try {
            boolean hasmore = true;
            while (hasmore) {
                Document doc =  Jsoup.connect("http://bing.plmeizi.com/show/"+id)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                        .timeout(60000).get();
                if (null != doc.selectFirst("#sf-resetcontent")) {
                    hasmore = false;
                } else {
                    String dateString = doc.select("#date").html();
                    String title = doc.select("#title").html();
                    String searchLink = doc.select("#searchlink").attr("href");
                    String picUrl = doc.select("#picurl").attr("href");

                    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDate date = LocalDate.parse(dateString, dtf);
                    BingImageArchive imageArchive = bingImageArchiveService.findCnImageByDate(date.plusDays(-1).format(dtf));
                    if (null == imageArchive) {
                        imageArchive = new BingImageArchive();
                    }
                    String imageTitle = title.split("<br>")[0];
                    String[] alts2 = imageTitle.split("\\(©", 2);
                    String[] alts1 = alts2[0].split("，", 2);

                    imageArchive.setImageTitle(alts1[0]);
                    imageArchive.setImagePlace(alts1.length>=2?alts1[1]:null);
                    imageArchive.setImageProvider(alts2.length>=2?"(@"+alts2[1]:null);
                    imageArchive.setOriginUrl(picUrl);
                    imageArchive.setImageDate(date.plusDays(-1).format(dtf));
                    imageArchive.setImageDateEnd(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    imageArchive.setImageAlt(title);
                    imageArchive.setImageZone(Constants.IMAGE_ZONE.CN.getValue());

                    imageArchive.setCopyrightlink(searchLink);
                    imageArchive.setCreateTime(new Date());

                    imageArchive.setImageUrl(BingUtils.BASE_DIR+"/"+imageArchive.getImageZone()+"/"+imageArchive.getImageDate());
                    FetchRetDto fetchRetDto = qiniuUtils.fetctToUpload(imageArchive.getOriginUrl(), imageArchive.getImageUrl());

                    bingImageArchiveService.save(imageArchive);
                    id = id+1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void histroy(LocalDate endDate) {
        if (null == endDate) {
            String date = bingImageArchiveService.maxDate();
            endDate = LocalDate.parse(date);
        }
        final LocalDate end = endDate;
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://bing.plmeizi.com/getpic", String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            String o = response.getBody().replace("\"[", "[").replace("]\"", "]").replace("\\\"", "'");
            ObjectMapper mapper = new ObjectMapper();
            try {
                BingDataMainDto bd = mapper.readValue(o,BingDataMainDto.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String body = response.getBody().replace("\\\"", "");
            Gson gs = new GsonBuilder()
                    .setPrettyPrinting()

                    .disableHtmlEscaping()
                    .create();
            BingDataMainDto bingDataDtos = gs.fromJson(body, BingDataMainDto.class);
            if (null != bingDataDtos && bingDataDtos.getData().size() > 0) {
                bingDataDtos.getData().stream().filter(x -> LocalDate.parse(x.getDate(), dtf).isAfter(end))
                        .forEach(x -> {
                            LocalDate date = LocalDate.parse(x.getDate(), dtf);
                            BingImageArchive imageArchive = new BingImageArchive();
                            imageArchive.setImageTitle(x.getTitle());
                            imageArchive.setOriginUrl(x.getImage());
                            imageArchive.setImageDate(date.plusDays(-1).format(dtf));
                            imageArchive.setImageDateEnd(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                            imageArchive.setImageAlt(x.getImage());
                            imageArchive.setImageZone(Constants.IMAGE_ZONE.CN.getValue());

                            imageArchive.setCopyrightlink(x.getSearchlink());
                            imageArchive.setCreateTime(new Date());

                            imageArchive.setImageUrl(BingUtils.BASE_DIR+"/"+imageArchive.getImageZone()+"/"+imageArchive.getImageDate());
                            FetchRetDto fetchRetDto = qiniuUtils.fetctToUpload(imageArchive.getOriginUrl(), imageArchive.getImageUrl());

                            bingImageArchiveService.save(imageArchive);
                        });
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = Jsoup.connect("http://bing.plmeizi.com/show/"+809)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000);
        System.out.println(connection.response().statusCode());
    }
}