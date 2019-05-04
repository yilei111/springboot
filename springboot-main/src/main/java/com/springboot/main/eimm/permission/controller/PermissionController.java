package com.springboot.main.eimm.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.main.eimm.permission.service.PermissionService;
import com.springboot.main.eimm.role.service.RoleService;

import lombok.extern.slf4j.Slf4j;
 

@Controller
@Slf4j
@RequestMapping("/permission")
public class PermissionController {

	
	@Autowired
	 PermissionService permissionService;
	
	
	 
	 
	 

}
