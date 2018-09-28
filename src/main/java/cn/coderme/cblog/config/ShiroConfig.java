//package cn.coderme.cblog.config;
//
//import org.apache.shiro.cache.CacheManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//
//    /**
//     * ShiroFilterFactoryBean 处理拦截资源文件问题。 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager * * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 * 3、部分过滤器可指定参数，如perms，roles *
//     */
//    @Bean
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
//        // 未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        // 拦截器.
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/ajaxLogin", "anon");
//
//        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//
////        filterChainDefinitionMap.put("/add", "perms[权限添加]");
//
//        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
////        filterChainDefinitionMap.put("/admin/**", "authc");
//        filterChainDefinitionMap.put("/admin/**", "authc");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 设置realm.
//        securityManager.setRealm(myShiroRealm());
//        // 自定义session管理 使用redis
////        securityManager.setSessionManager(sessionManager());
//        // 自定义缓存实现 使用redis
////        securityManager.setCacheManager((CacheManager) redisCacheManager);
//        return securityManager;
//    }
//    /**
//     * 身份认证realm
//     * @return
//     */
//    @Bean
//    public MyShiroRealm myShiroRealm() {
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        return myShiroRealm;
//    }
//}
