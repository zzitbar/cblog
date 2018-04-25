package cn.coderme.cblog.qiniu;

import com.qiniu.util.Auth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By zhangtengfei
 * Date:2018/4/23
 * Time:14:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QiniuTest {

    @Test
    public void test() {
        String key = "file key";
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket, key);
//        System.out.println(upToken);
    }
}