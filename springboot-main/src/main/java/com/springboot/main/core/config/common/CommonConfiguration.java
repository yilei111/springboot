package com.springboot.main.core.config.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * @method 公共变量-全局变量 配置
 * @author Mr yi
 * @time 2019年5月6日
 */
@Configuration
public class CommonConfiguration {

	@Resource
    private Environment env;
	
	
	/**
	 * @method 全局变量配置(配置静态资源服务的路径） http://127.0.0.1:8001/spring-resource/
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param viewResolver
	 */
    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars =  new HashMap<String, Object>();
            //spring-main 模块
            String spring_main = env.getProperty("system.spring-main");
            vars.put("spring_main", spring_main);
            //spring-resource 模块
            String spring_resource = env.getProperty("system.spring-resource");
            vars.put("spring_resource", spring_resource);
            viewResolver.setStaticVariables(vars);
        }
    }
    
    /**
     * 前端js中使用如下接收
     * <script th:inline="javascript">
       var baseUrl = '[(${baseUrl})]';
		</script>
     */
}
