package com.springboot.main.core.config.mybatis_plus;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.expression.Maps;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * mybatis plus 配置
 * @author Mr yi
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
