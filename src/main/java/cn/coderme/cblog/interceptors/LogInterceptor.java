package cn.coderme.cblog.interceptors;

import cn.coderme.cblog.service.ApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录接口请求日志
 * Created By Administrator
 * Date:2018/8/16
 * Time:11:07
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

    @Autowired
    private ApiLogService apiLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTimeThreadLocal.set(System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求耗时
        Long costTime = System.currentTimeMillis() - startTimeThreadLocal.get();
        startTimeThreadLocal.remove();
        // 保存日志
        apiLogService.saveLog(request, ex, costTime.intValue());
        super.afterCompletion(request, response, handler, ex);
    }
}