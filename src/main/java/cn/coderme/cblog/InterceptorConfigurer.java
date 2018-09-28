package cn.coderme.cblog;

import cn.coderme.cblog.interceptors.LogInterceptor;
import cn.coderme.cblog.interceptors.RateLimitingInterceptor;
import cn.coderme.cblog.interceptors.TokenInterceptor;
import org.springframework.context.annotation.Bean;
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
        registry.addInterceptor(getRateLimitingInterceptor())    // 接口限流拦截器类
                .addPathPatterns("/api/**");        // 指定该类拦截的url
        registry.addInterceptor(getLogInterceptor())    // 接口日志记录拦截器类
                .addPathPatterns("/api/**");        // 指定该类拦截的url
        registry.addInterceptor(getTokenInterceptor())    // 后台管理拦截器类
                .addPathPatterns("/admin/**", "/user/**");        // 指定该类拦截的url
    }

    @Bean
    public RateLimitingInterceptor getRateLimitingInterceptor() {
        return new RateLimitingInterceptor();
    }

    @Bean
    public LogInterceptor getLogInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }
}