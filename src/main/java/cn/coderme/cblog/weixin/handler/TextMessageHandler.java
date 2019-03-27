package cn.coderme.cblog.weixin.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

import java.util.Map;

/**
 * 微信公众号用户发送文本消息后，服务器处理
 * Created By zzitbar
 * Date:2018/10/30
 * Time:14:41
 */
public class TextMessageHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        WxMpXmlOutTextMessage m
                = WxMpXmlOutMessage.TEXT().content(wxMessage.getContent()).fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
        return m;
    }
}