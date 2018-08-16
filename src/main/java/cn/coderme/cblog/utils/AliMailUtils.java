package cn.coderme.cblog.utils;

import cn.coderme.cblog.base.MailKey;
import com.aliyuncs.dm.simple.Email;
import com.aliyuncs.dm.simple.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created By Administrator
 * Date:2018/8/14
 * Time:11:00
 */
@Component
public class AliMailUtils {

    private static final Logger logger = LoggerFactory.getLogger(AliMailUtils.class);

    private MailSender mailSender;
    @Autowired
    private MailKey mailKey;

    @PostConstruct
    public void initMethod() {
        mailSender = new MailSender(mailKey.getHost(), mailKey.getPort(), mailKey.getUsername(), mailKey.getPassword());
    }

    /**
     * 发送验证码
     * @param mailAddress
     * @param code
     */
    public void sendValidateCode(String mailAddress, String code) {
        Map param = new HashMap();
        param.put("mailAddress", mailAddress);
        param.put("code", code);
        String text = FreeMarkerUtil.toHtml("mail.ftl", param);
        mailTo(" Bing 壁纸 API 激活邮箱验证 ", text, mailAddress);
    }

    /**
     * 向单个或多个人发送邮件
     * @param mailAddress
     */
    public void mailTo(String subject, String text, String... mailAddress) {
        Email email = Email.builder()
                .from(mailKey.getUsername())
                .to(Arrays.asList(mailAddress))
                .subject(subject)
                .textHTML(text)
                .replyToAddress(new Email.Address(mailKey.getUsername(), ""))
                .build();

        String result = mailSender.sendMail(email);
        logger.info(result);
    }



    public static void main(String[] args) {
        AliMailUtils aliMailUtils = new AliMailUtils();
        aliMailUtils.mailKey = new MailKey();
        aliMailUtils.mailKey.setUsername("service@mail.zzitbar.com");
        aliMailUtils.mailKey.setPassword("ZHtf714593351");
        aliMailUtils.mailKey.setHost("smtpdm.aliyun.com");
        aliMailUtils.mailKey.setPort("25");

        aliMailUtils.mailSender = new MailSender(aliMailUtils.mailKey.getHost(), aliMailUtils.mailKey.getPort(),
                aliMailUtils.mailKey.getUsername(), aliMailUtils.mailKey.getPassword());

        Map param = new HashMap();
        param.put("mailAddress", "Big Joe");
        param.put("code", "1edfdf");
        String text = FreeMarkerUtil.toHtml("mail.ftl", param);

        aliMailUtils.sendValidateCode("714593351@qq.com", text);
    }
}