package cn.coderme.cblog.jobs;

import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.service.bing.BingImageArchiveService;
import cn.coderme.cblog.utils.BingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
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

    /**
     * 每天凌晨3点触发
     */
    @Scheduled(cron="0 0 3 * * ?")
//    @Scheduled(fixedRate = 30000)
    public void everyday() {
        String date = bingImageArchiveService.maxDate();
        LocalDate maxDate = LocalDate.parse(date);
        StopWatch stopWatch = new StopWatch("获取BING壁纸定时任务");
        stopWatch.start();
        while (maxDate.isBefore(LocalDate.now())) {
            try {
                List<BingImageArchive> imageArchives = bingUtils.downloadImage(maxDate.getYear(), maxDate.getMonthValue(), maxDate.getDayOfMonth());
                if (null != imageArchives && imageArchives.size()>0) {
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
}