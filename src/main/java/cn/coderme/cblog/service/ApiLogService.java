package cn.coderme.cblog.service;

import cn.coderme.cblog.dao.ApiLogDAO;
import cn.coderme.cblog.entity.ApiLog;
import cn.coderme.cblog.entity.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * API 请求日志 Service
 * Created By Administrator
 * Date:2018/8/16
 * Time:11:17
 */
@Service
public class ApiLogService {

    @Autowired
    private ApiLogDAO apiLogDAO;
    @Autowired
    private UserService userService;

    @Transactional
    @Async
    public void saveLog(HttpServletRequest request, Exception ex, int costTime) {
        ApiLog log = new ApiLog();
        log.setStatus(ex == null ? 1:0);
        log.setRemoteAddr(getRemoteAddr(request));
        log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        Gson gson = new Gson();
        String params = gson.toJson(request.getParameterMap());
        log.setParams(params);
        log.setMethod(request.getMethod());
        log.setCreateTime(new Date());
        log.setCostTime(costTime);

        // AppSecret
        String appSecret = request.getHeader("AppSecret");
        log.setAppSecret(appSecret);
        if (StringUtils.hasText(appSecret)) {
            User user = userService.findByAppSecret(appSecret);
            log.setUserId(null==user?null:user.getId());
        }

        // 异常信息
        if (ex != null){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            log.setException(stringWriter.toString());
        }

        apiLogDAO.save(log);
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.hasText(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (StringUtils.hasText(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (StringUtils.hasText(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return (remoteAddr != null ? remoteAddr : request.getRemoteAddr()).split(",")[0];// 有时会出现以，分割的多个IP
    }
}