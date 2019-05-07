package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主启动入口 
 * @author Mr yi
 */
@SpringBootApplication
public class SpringBootMainApplication   {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMainApplication.class, args);
	}
	

}
