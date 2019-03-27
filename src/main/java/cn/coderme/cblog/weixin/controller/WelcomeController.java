package cn.coderme.cblog.weixin.controller;

import cn.coderme.cblog.weixin.service.WeixinService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created By zzitbar
 * Date:2018/10/30
 * Time:13:54
 */
@RestController
@RequestMapping("/weixin")
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeixinService weixinService;

    @GetMapping(value = "")
    public String auth(String signature, String timestamp, String nonce, String echostr) {
        if (weixinService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        } else {
            return "授权出错";
        }
    }

    @PostMapping(value = "")
    public String post(HttpServletRequest request) {
        String result = "";
        try {
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = weixinService.route(inMessage);
            result = (outMessage != null ? outMessage.toXml() : "");
        } catch (IOException e) {
            logger.error("收到消息处理出错", e);
        }
        return result;
    }

    @GetMapping("/createMenu")
    public String createMenu() {
        WxMenu wxMenu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.MenuButtonType.VIEW);
        button1.setName("Bing壁纸");
        button1.setUrl("http://zzitbar.com/bing");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.MenuButtonType.VIEW);
        button2.setName("关于");
        button2.setKey("关于");

        wxMenu.getButtons().add(button1);
        wxMenu.getButtons().add(button2);
        String result = "success";
        try {
            weixinService.getMenuService().menuCreate(wxMenu);
        } catch (WxErrorException e) {
            logger.error("创建菜单出错", e);
            result = e.getMessage();
        }
        return result;
    }
}