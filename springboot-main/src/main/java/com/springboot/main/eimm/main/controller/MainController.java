package com.springboot.main.eimm.main.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.main.core.controller.M;
import com.springboot.main.core.util.RedisUtils;
import com.springboot.main.eimm.user.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @method 程序主程序控制层方法（登录-注销）
 * @author Mr yi
 * @time 2019年5月6日
 */
@Controller
@Slf4j
@RequestMapping("main")
public class MainController {

	private RedisUtils redisUtils=new RedisUtils();
	/**
	 * 
	 * @method 通用访问页面方法（访问方式：http://127.0.0.1:8080/spring-main/main/main/content） 访问main文件夹下的content。html页面
	 * @author Mr yi
	 * @time 2019年5月9日
	 * @param module
	 * @param usecase
	 * @return
	 */
	@RequestMapping("/{module}/{usecase}")
	public String index(@PathVariable("module")String module,@PathVariable("usecase")String usecase){				
		return M.goTo(module+"/"+usecase);
	}
	
	/**
	 * @method 用户登录（get)
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return M.goTo("login");
	}

	/**
	 * @method 用户登录（post)
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param request
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, User user, Model model,boolean rememberMe) {
		if (StringUtils.isEmpty(user.getUser_name()) || StringUtils.isEmpty(user.getUser_pwd())) {
			request.setAttribute("msg", "账号或密码不能为空！");
			return M.goTo("login");
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name(), user.getUser_pwd(),rememberMe);
		//获取当前的Subject  
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return M.redirectTo("main/index");
		} catch (LockedAccountException lae) {
			token.clear();
			request.setAttribute("msg", "该账号已被锁定！");
		} catch (DisabledAccountException e) {
			token.clear();
			request.setAttribute("msg", "该账号已被禁用！");
		} catch (AuthenticationException e) {
			token.clear();
			request.setAttribute("msg", "账号或密码不正确！");
		}
		return M.goTo("login");
	}


	/**
	 * @method 注销（logout方法自动清空shiro相关用户缓存)
	 * @author Mr yi 
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout1() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		log.info(" 用户注销成功 ！");
		//后台需要清空此用户全部缓存信息
		 
		return M.goTo("login");
	}
	
	
	
}
