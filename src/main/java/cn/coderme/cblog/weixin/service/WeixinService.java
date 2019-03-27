package cn.coderme.cblog.weixin.service;

import cn.coderme.cblog.weixin.WxConfig;
import cn.coderme.cblog.weixin.handler.TextMessageHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created By zzitbar
 * Date:2018/10/30
 * Time:13:58
 */
@Service
public class WeixinService extends WxMpServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxConfig wxConfig;

    private WxMpMessageRouter router;

    @PostConstruct
    public void init() {
        final WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wxConfig.getAppid()); // 设置微信公众号的appid
        wxMpConfigStorage.setSecret(wxConfig.getAppsecret()); // 设置微信公众号的appsecret
        wxMpConfigStorage.setToken(wxConfig.getToken()); // 设置微信公众号的token
//        wxMpConfigStorage.setJedis(jedisPool.getResource());
        super.setWxMpConfigStorage(wxMpConfigStorage);
//        refreshRouter();

        router = new WxMpMessageRouter(this);
        router.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(new TextMessageHandler()).end();
    }

    public WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.router.route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }
}