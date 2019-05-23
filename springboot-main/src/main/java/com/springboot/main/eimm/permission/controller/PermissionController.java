package com.springboot.main.eimm.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.common.core.common.RedisEnum;
import com.springboot.main.core.controller.M;
import com.springboot.main.core.util.RedisUtils;
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
	@Autowired
	private RedisUtils redisUtils;
	@Resource
    private Environment env;
	
	/**
	 * 
	 * @method 获取用户的菜单权限（有些从缓存中获取）
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/loadMenu")
	@ResponseBody
	public void loadMenu(HttpServletRequest request, HttpServletResponse response) {
		String  userId =  String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
		String key =userId+ RedisEnum.REDIS_USER_MENU_LIST.getName();////用户菜单列表key
		
		boolean hasKey =redisUtils.hasKey(key);
		//直接获取redis缓存中用户菜单列表信息
		if(hasKey) {
			log.info("直接获取redis缓存中用户菜单列表信息:"+redisUtils.get(key));
			M.ajax(response,  redisUtils.get(key));
		}else {
			String  menu =  formartMenu(userId);
			if(StringUtils.isNotBlank(menu)) {
				redisUtils.set(key, menu,Long.parseLong(RedisEnum.REDIS_EXPIRE_TIME.getName())); //半个小时过期
				log.info("将用户菜单列表信息缓存到redis中");
			}
			log.info("从数据库获取用户菜单列表信息");
			M.ajax(response, menu);
		}
		
	}
	
	//加载父节点菜单
	private String formartMenu (String userId  ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_state", "001");//角色状态：启用
		map.put("user_id",userId);//用户id
		map.put("permission_state", "001");//权限状态：启用
		map.put("permission_type", "001"); //权限类型：菜单
		
		map.put("parent_id", "0"); //父级菜单
		List<Permission> permissionParentList = permissionService.getPermissionListByUserId(map);
		map.put("parent_id", "1"); //子级菜单
		List<Permission> permissionSonList = permissionService.getPermissionListByUserId(map);
		
		StringBuffer html = new StringBuffer();
		for (Permission permissionParent : permissionParentList) {
			
			String  id = permissionParent.getId();
			//加载父节点菜单
			html.append("<li class=\"\">  ");
			if(StringUtils.isBlank(permissionParent.getPermission_url())) {
				html.append("<a href=\"#\" class=\"dropdown-toggle\">  ");
			}else {
				
				
				
				html.append("<a href=\"#\" onclick=\"loadPage('"+formartUrl(permissionParent)+"')\"> ");
			}
			html.append("		<i class=\"menu-icon fa fa-tachometer\"></i>  ");
			html.append("		<span class=\"menu-text\">"+permissionParent.getPermission_name()+" </span>  ");
			if(StringUtils.isBlank(permissionParent.getPermission_url())) {
				html.append("<b class=\"arrow fa fa-angle-down\"></b>  ");
			}else {
				html.append("<b class=\"arrow\"></b>  ");
			}
			html.append("	</a>  ");
			//加载子节点菜单
			html.append(formartMenu(permissionSonList,id) );
			html.append("</li>  ");
		}	
		return html.toString();
	}
	
	//生成菜单url
	@SuppressWarnings("unused")
	private String formartUrl(Permission permission) {
		String spring_module = env.getProperty("system."+permission.getPermission_module());
		 return spring_module+permission.getPermission_url();
	}
	
	//加载子节点菜单
	private String formartMenu(List<Permission> permissionSonList,String id ) {
		StringBuffer html = new StringBuffer(" ");
		for (Permission permissionSon : permissionSonList) {
			if(StringUtils.equals(permissionSon.getParent_id(), id)) {
				html.append("<li class=\"\"> ");
				if(StringUtils.isBlank(permissionSon.getPermission_url())) {
					html.append("<a href=\"#\" class=\"dropdown-toggle\">  ");
				}else {
					html.append("<a href=\"#\" onclick=\"loadPage('"+formartUrl(permissionSon)+"')\"> ");
				}
				html.append("		<i class=\"menu-icon fa fa-caret-right\"></i>"+permissionSon.getPermission_name());
				if(StringUtils.isBlank(permissionSon.getPermission_url())) {
					html.append("	<b class=\"arrow fa fa-angle-down\"></b>  ");
				}else {
					html.append("	<b class=\"arrow\"></b>  ");
				}
				html.append("	</a>  ");
				html.append(formartMenu(permissionSonList,permissionSon.getId()));
				html.append("</li>  ");
			} 
		}
		if(StringUtils.isNotBlank(html.toString())) {
			return  "<ul class=\"submenu\">" +html.toString() +"</ul>";
		}
		return  html.toString();
	}

}
