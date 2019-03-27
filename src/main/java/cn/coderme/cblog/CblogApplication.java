package cn.coderme.cblog;

import cn.coderme.cblog.base.TextJson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching // 启动缓存
@EnableScheduling// 启用定时
@EnableRedisHttpSession //启用session redis
// 支持java8 新日期类
@EntityScan(
        basePackageClasses = {CblogApplication.class, Jsr310JpaConverters.class}
)
//@MapperScan("cn.coderme.cblog.dao")
public class CblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CblogApplication.class, args);
	}

	/**
	 * 重新配置RedisCacheManager
	 * @param rd
	 */
	@Autowired
	public void configRedisCacheManger(RedisCacheManager rd){
		rd.setDefaultExpiration(1000L);
	}

	/**
	 * 注入 RestTemplate
	 * @param factory
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new TextJson2HttpMessageConverter());
        return restTemplate;
	}
	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);//ms
		factory.setConnectTimeout(15000);//ms
		return factory;
	}
}
