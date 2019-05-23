package com.springboot.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/**
 *  
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) 
public class SpringBootActivityApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootActivityApplication.class, args);
    }
}