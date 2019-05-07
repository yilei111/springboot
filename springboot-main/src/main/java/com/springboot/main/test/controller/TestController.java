package com.springboot.main.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.springboot.main.eimm.user.controller.UserController;
import com.springboot.main.test.entity.Test;
import com.springboot.main.test.service.TestService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户user控制层
 * @author Mr yi
 */
@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {

	
	@Autowired
	TestService userService;
	
	@Autowired
    private RedisTemplate redisTemplate;

	/**
	 * 获取全部数据
	 * @author Mr yi
	 * @return
	 */
	@RequestMapping("/getUserList")
	public List<Test> getUserList() {
		return userService.getTestList();
	}

	/**
	 * 获取全部数据-条件查询
	 * @author Mr yi
	 * @return
	 */
	@RequestMapping("/getUserListByName")
	public List<Test> getUserListByName(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", userName);
		return userService.selectByMap(map);
	}

	/**
	 * 保存用户
	 * @author Mr yi
	 * @return
	 */
	@RequestMapping("/saveUser")
	public String saveUser(String userName, String userPassword) {
		Test user = new Test();
		user.setName("admin");
		user.setAge(12);
		Integer index = userService.insert(user);
		if (index > 0) {
			return "新增用户成功。";
		} else {
			return "新增用户失败。";
		}
	}

	/**
	 * 修改用户
	 * @author Mr yi
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(Integer id, String userName, String userPassword) {
		Test user = new Test();
		user.setId(11);
		user.setAge(55);
		user.setName("hh");
		Integer index = userService.updateById(user);
		if (index > 0) {
			return "修改用户成功，影响行数" + index + "行。";
		} else {
			return "修改用户失败，影响行数" + index + "行。";
		}
	}

	/**
	 * 查询用户-通过id
	 * @author Mr yi
	 * @return
	 */
	@RequestMapping("/getUserById")
	public Test getUserById(Integer userId) {
		return userService.selectById(userId);
	}

	/**
	 * 分页条件查询
	 * @author Mr yi
	 * @return
	 */
	@RequestMapping("/getUserListByPage")
	public List<Test> getUserListByPage(Integer pageNumber, Integer pageSize) {
		Page<Test> page = new Page<>(pageNumber, pageSize);
		EntityWrapper<Test> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("name", "name");
		return userService.selectPage(page, entityWrapper);
	}
	
	 
    
	
    /**
     * redis 的测试方法（添加、获取、删除）
     * @return
     * @throws InterruptedException
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getReids")
    public String getReids() throws InterruptedException {
		Test user=new Test( );
    	 user.setId(1).setName("Mr yi").setAge(10);
         redisTemplate.opsForValue().set("uUserTest", user);
         Object uu = redisTemplate.opsForValue().get("uUserTest");
         redisTemplate.delete("uUserTest");
         log.info("redis中获取数据：[{}]", uu);
         return uu.toString();
    }
    	
	/**
	 * hibernate-validator  表单验证测试方法
	 * @param demo
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/testDemo")
    public String testDemo(@Valid Test demo,BindingResult bindingResult){
        StringBuffer stringBuffer = new StringBuffer();
        if(bindingResult.hasErrors()){
            List<ObjectError> list =bindingResult.getAllErrors();
            for (ObjectError objectError:list) {
                stringBuffer.append(objectError.getDefaultMessage());
                stringBuffer.append("---");
            }
        }
        return stringBuffer!=null?stringBuffer.toString():"";
    }

	/**
	 * 获取全部数据
	 * @author Mr yi
	 * @return
	 */
	//@CrossOrigin(origins = "*")
	@RequestMapping("/blank")
	public String blank() {
		return "blank";
	}
	 

}
