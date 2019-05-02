package com.springboot.main.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * 公共变量-全局变量 配置
 * @author Mr yi
 */
@Configuration
public class CommonConfiguration {

	@Resource
    private Environment env;
    
	/**
	 * 全局变量配置(配置静态资源服务的路径） http://127.0.0.1:8001/spring-resource/
	 * @param viewResolver
	 */
    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars =  new HashMap<String, Object>();
            String baseUrl = env.getProperty("resource.protocol")+"://"+env.getProperty("resource.ip")
            				+":"+env.getProperty("resource.port")+"/"+env.getProperty("resource.project_name")+"/";
            vars.put("baseUrl", baseUrl);
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
