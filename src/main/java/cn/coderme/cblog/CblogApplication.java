package cn.coderme.cblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;

@SpringBootApplication
@EnableCaching // 启动缓存
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
}
