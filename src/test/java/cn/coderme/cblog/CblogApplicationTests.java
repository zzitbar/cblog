package cn.coderme.cblog;

import cn.coderme.cblog.base.ApplicationContextProvider;
import cn.coderme.cblog.service.hotnews.crawl.HotnewsCrawlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CblogApplicationTests {

	@Test
	public void contextLoads() {
        String[] beanNames = ApplicationContextProvider.getApplicationContext().getBeanNamesForType(HotnewsCrawlService.class);
        for (String beanName : beanNames) {
            HotnewsCrawlService hotnewsCrawlService = (HotnewsCrawlService) ApplicationContextProvider.getBean(beanName);
            System.out.println(hotnewsCrawlService);
        }
	}

}
