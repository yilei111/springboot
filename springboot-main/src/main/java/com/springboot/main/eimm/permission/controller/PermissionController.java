package com.springboot.main.eimm.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.common.core.common.RedisEnum;
import com.springboot.main.core.controller.M;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.permission.service.PermissionService;
import com.springboot.main.eimm.role.service.RoleService;

import lombok.extern.slf4j.Slf4j;
 
@Controller
@Slf4j
@RequestMapping("/permission")
/**
 * @method 权限资源控制层
 * @author Mr yi
 * @time 2019年5月6日
 */
public class PermissionController {

	@Autowired
	 PermissionService permissionService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
	
	/**
	 * 
	 * @method 获取用户的菜单权限（有些从缓存中获取）
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/loadMenu")
	@ResponseBody
	public void loadMenu(HttpServletRequest request, HttpServletResponse response) {
		
		String key = RedisEnum.REDIS_USER_MENU_LIST.getName();////用户菜单列表key
		ValueOperations<String, List<Permission>> operations = redisTemplate.opsForValue();
		boolean hasKey = redisTemplate.hasKey(key);
		//直接获取redis缓存中用户菜单列表信息
		if(hasKey) {
			log.info("直接获取redis缓存中用户菜单列表信息:"+operations.get(key).toString());
			M.ajax(response, operations.get(key));
		}else {
			String  userId =  String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("role_state", "001");//角色状态：启用
			map.put("user_id",userId);//用户id
			map.put("permission_state", "001");//权限状态：启用
			map.put("permission_type", "001"); //权限类型：菜单
			List<Permission> permissionList = permissionService.getPermissionListByUserId(map);
			if(null !=permissionList) {
				operations.set(key, permissionList);
				log.info("将用户菜单列表信息缓存到redis中");
			}
			log.info("从数据库获取用户菜单列表信息");
			M.ajax(response, permissionList);
		}
		
	}

}
