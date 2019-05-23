package com.springboot.main.eimm.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.common.core.common.RedisEnum;
import com.springboot.main.core.controller.M;
import com.springboot.main.core.util.RedisUtils;
import com.springboot.main.eimm.user.entity.User;
import com.springboot.main.eimm.user.service.UserService;
import com.springboot.main.test.entity.Test;

import lombok.extern.slf4j.Slf4j;
 
/**
 * @method 用户控制层
 * @author Mr yi
 * @time 2019年5月6日
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	UserService userService;
 
	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * @method 获取用户列表
	 * @author Mr yi 
	 * @time 2019年5月6日
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/getUserList")
	public String getUserList(Model model) {
		 
		String  userId =  String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
		String key =userId+ RedisEnum.REDIS_USER_LIST.getName();////用户菜单列表key
		boolean hasKey =redisUtils.hasKey(key);
		
		List<User> list = new ArrayList<User>();
	 
		if(hasKey) {
			log.info("从redis中获取用户列表");
			list =  (List<User>) redisUtils.get(key);
		}else {
			list = userService.selectList(null);
			redisUtils.set(key, list,Long.parseLong(RedisEnum.REDIS_EXPIRE_TIME.getName()));
		}
		System.out.println(list);
		model.addAttribute("list", list);
		return M.goTo("user/user_list");
	}
	

}
