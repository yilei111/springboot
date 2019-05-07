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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springboot.main.eimm.user.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @method 程序主程序控制层方法（登录注销）
 * @author Mr yi
 * @time 2019年5月6日
 */
@Controller
@Slf4j
@RequestMapping("/main")
public class MainController {

	/**
	 * @method 用户登录（get)
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
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
			return "login";
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name(), user.getUser_pwd(),rememberMe);
		//获取当前的Subject  
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return "redirect:blank";
		} catch (LockedAccountException lae) {
			token.clear();
			request.setAttribute("msg", "该账号已被锁定！");
			return "login";
		} catch (DisabledAccountException e) {
			token.clear();
			request.setAttribute("msg", "该账号已被禁用！");
			return "login";
		} catch (AuthenticationException e) {
			token.clear();
			request.setAttribute("msg", "账号或密码不正确！");
			return "login";
		}
	}

	/**
	 * @method
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping("/blank")
	public String blank() {
		return "blank";
	}

	/**
	 * @method 404异常响声界面
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping("/404")
	public String error404() {
		log.error("404异常发生！");
		return "error/error-404";
	}
	/**
	 * @method 404异常响声界面
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping("/405")
	public String error405() {
		log.error("404异常发生！");
		return "error/error-404";
	}

	/**
	 * @method 注销（logout方法自动清空shiro相关用户缓存)
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		log.info("用户注销成功！");
		return "login";
	}

}
