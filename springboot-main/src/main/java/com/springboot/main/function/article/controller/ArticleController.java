package com.springboot.main.function.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.main.core.controller.M;
import com.springboot.main.function.article.service.ArticleService;
import com.springboot.main.function.file.controller.FileController;
import com.springboot.main.function.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	 
	
}
