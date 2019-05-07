package com.springboot.main.core.config.mybatis_plus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
/**
 * @method mybatis plus 配置
 * @author Mr yi
 * @time 2019年5月6日
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        return page;
    }
    
    
}
