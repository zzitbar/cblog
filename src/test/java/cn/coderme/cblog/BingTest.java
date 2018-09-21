package cn.coderme.cblog;

import cn.coderme.cblog.jobs.BingImageJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By Administrator
 * Date:2018/8/10
 * Time:14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BingTest {

    @Autowired
    private BingImageJob bingImageJob;

    @Test
    public void histroy() {
        bingImageJob.history(778);
    }
}