package cn.coderme.cblog;

import cn.coderme.cblog.interceptors.RateLimitingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created By Administrator
 * Date:2018/5/11
 * Time:16:25
 */
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter {

    //增加拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new RateLimitingInterceptor())    //指定拦截器类
                .addPathPatterns("/rest/**");        //指定该类拦截的url
    }
}