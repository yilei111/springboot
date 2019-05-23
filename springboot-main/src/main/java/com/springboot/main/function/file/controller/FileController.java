package com.springboot.main.function.file.controller;

import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.main.core.controller.M;
import com.springboot.main.eimm.main.controller.MainController;
import com.springboot.main.function.file.dao.FileMapper;
import com.springboot.main.function.file.entity.File;
import com.springboot.main.function.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;
	
	@RequestMapping("/getList")
	public String getList() {
		 
		return M.goTo("login");
	}
	
}
