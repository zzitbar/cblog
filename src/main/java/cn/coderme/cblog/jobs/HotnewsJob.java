package cn.coderme.cblog.jobs;

import cn.coderme.cblog.base.ApplicationContextProvider;
import cn.coderme.cblog.service.hotnews.crawl.BaiduHotnewsService;
import cn.coderme.cblog.service.hotnews.crawl.HotnewsCrawlService;
import cn.coderme.cblog.service.hotnews.crawl.WeiboHotnewsService;
import cn.coderme.cblog.service.hotnews.crawl.ZhihuHotnewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created By zzitbar
 * Date:2018/11/14
 * Time:14:05
 */
@Component
public class HotnewsJob {

    /**
     * 定时任务抓取热点，每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void updateWeiboNews() {
        try {
            String[] beanNames = ApplicationContextProvider.getApplicationContext().getBeanNamesForType(HotnewsCrawlService.class);
            for (String beanName : beanNames) {
                HotnewsCrawlService hotnewsCrawlService = (HotnewsCrawlService) ApplicationContextProvider.getBean(beanName);
                hotnewsCrawlService.updateNews();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}