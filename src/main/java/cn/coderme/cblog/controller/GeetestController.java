package cn.coderme.cblog.controller;

import cn.coderme.cblog.base.GeetestConfig;
import cn.coderme.cblog.utils.GeetestLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 极验 验证码服务
 * Created By Administrator
 * Date:2018/8/14
 * Time:14:57
 */
@Controller
@RequestMapping("/geetest")
public class GeetestController {

    @Autowired
    private GeetestConfig geetestConfig;

    @RequestMapping(value = "/startCaptcha", method = RequestMethod.GET)
    @ResponseBody
    public String startCaptcha(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(geetestConfig.getGeetestId(), geetestConfig.getGeetestKey(),
                geetestConfig.isnewfailback());
        String resStr = "{}";
        String userid = request.getSession().getId();

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", request.getRemoteAddr()); //传输用户请求验证时所携带的IP

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();
        return resStr;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public Map verify(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(geetestConfig.getGeetestId(), geetestConfig.getGeetestKey(),
                geetestConfig.isnewfailback());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        //从session中获取userid
        String userid = (String)request.getSession().getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", request.getRemoteAddr()); //传输用户请求验证时所携带的IP

        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }

        Map result = new HashMap();
        result.put("status", gtResult == 1?"success":"fail");
        result.put("version", gtSdk.getVersionInfo());
        return result;
    }
}