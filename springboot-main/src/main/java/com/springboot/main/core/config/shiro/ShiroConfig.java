package com.springboot.main.core.config.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.crazycake.shiro.RedisCacheManager;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.permission.service.PermissionService;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.extern.slf4j.Slf4j;

/**
 * @method shiro核心配置
 * @author Mr yi
 * @time 2019年5月6日
 */
@Configuration
@Slf4j
public class ShiroConfig {

	@Autowired(required = false)
	private PermissionService permissionService;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.lettuce.pool.timeout}")
	private int timeout;

	@Value("${spring.redis.password}")
	private String password;

	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	
	
    
	/**
	 * @method ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		log.info("配置thymeleaf方言shiroDialect");
		return new ShiroDialect();
	}
	
	
	/**
	 * 
	 * @method ShiroFilterFactoryBean 处理拦截资源文件问题。
	 *         注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
	 *         初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager Filter Chain定义说明
	 *         1、一个URL可以配置多个Filter，使用逗号分隔
	 *         2、当设置多个过滤器时，全部验证通过，才视为通过3、部分过滤器可指定参数，如perms，roles
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		log.info("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/main/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/main/blank");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/main/404");
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		filterChainDefinitionMap.put("/", "user");
		filterChainDefinitionMap.put("/main/blank", "user");
		
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了,这里我们在MainController自定义退出方法
		filterChainDefinitionMap.put("/**","anon");
		
		/* filterChainDefinitionMap.put("/main/logout", "logout"); */
		/*
		 * filterChainDefinitionMap.put("/css/**","anon");
		 * filterChainDefinitionMap.put("/js/**","anon");
		 * filterChainDefinitionMap.put("/img/**","anon");
		 * filterChainDefinitionMap.put("/font-awesome/**","anon");
		 */
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		// 自定义加载权限资源关系
		List<Permission> permissionList = permissionService.selectByMap(new HashMap<String, Object>());
		for (Permission permission : permissionList) {
			if (StringUtils.isNotEmpty(permission.getPermission_url())) {
				String url = "perms[" + permission.getPermission_url() + "]";
				filterChainDefinitionMap.put(permission.getPermission_url(), url);
			}
		}
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	
	//配置核心安全事务管理器
    @Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		//注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}
	/**
	 * @method MyShiroRealm自定义Realm-配置自定义的权限登录器
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * @method 凭证匹配器
	 *         （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了,所以我们需要修改下doGetAuthenticationInfo中的代码;
	 *         ）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * @method 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * @method 配置shiro redisManager 使用的是shiro-redis开源插件
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(timeout);
		redisManager.setPassword(password);
		return redisManager;
	}

	/**
	 * @method cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * @method RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * @method shiro session的管理-使用的是shiro-redis开源插件
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	/**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间7天 ,单位秒;-->
        simpleCookie.setMaxAge(604800);
        return simpleCookie;
    }
    
    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

}
