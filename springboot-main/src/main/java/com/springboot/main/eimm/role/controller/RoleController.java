package com.springboot.main.eimm.role.controller;

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

import com.springboot.main.eimm.role.service.RoleService;

import lombok.extern.slf4j.Slf4j;
 
/**
 * @method 角色控制层
 * @author Mr yi
 * @time 2019年5月6日
 */
@Controller
@Slf4j
@RequestMapping("/role")
public class RoleController {

	
	@Autowired
	RoleService roleService;
	
	
	 
	 

}
