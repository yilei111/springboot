package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主启动入口 
 * @author Mr yi
 */
@SpringBootApplication

public class Application   {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

}
