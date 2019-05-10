package com.springboot.main.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @method Controller返回结果
 * @author Mr yi
 * @time 2019年5月10日
 */
@Slf4j
public class M implements Serializable{
	private static final long serialVersionUID = -4853557006344712708L;
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_ERROR = "error";
	/**
	 * 消息关键字
	 */
	private static final String MSG_KEY = "springMvcMessage";
	/**
	 * 跳转地址
	 */
	private String url;
	/**
	 * 消息
	 */
	private String message;
	/**
	 * 结果类型
	 */
	private String status;
	/**
	 * jsFunction
	 */
	private String jsFunction;
	/**
	 * 数据
	 */
	private Object data;

	/**
	 * 返回消息
	 * 
	 * @return
	 */
	public M msg(String msg) {
		message = msg;
		return this;
	}

	/**
	 * 本地跳转
	 * 
	 * @param url
	 * @return
	 */
	public M redirect(String url) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ctxPath = request.getContextPath();
		this.url = ctxPath + url;
		return this;
	}

	/**
	 * 远程跳转
	 * 
	 * @param url
	 * @return
	 */
	public M remote(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 返回前调用JS代码
	 * 
	 * @param js
	 * @return
	 */
	public M js(String js) {
		this.jsFunction = js;
		return this;
	}

	/**
	 * 创建错误
	 * 
	 * @param model
	 * @return
	 */
	public static M error(Model model) {
		M message = new M();
		model.addAttribute(MSG_KEY, message);
		message.status = STATUS_ERROR;
		message.message = "操作失败!";
		return message;
	}
	
	/**
	 * 创建错误
	 * 
	 * @return
	 */
	public static M error() {
		M message = new M();
		message.status = STATUS_ERROR;
		message.message = "操作失败!";
		return message;
	}
	
	/**
	 * 创建错误
	 * 
	 * @param model
	 * @return
	 */
	public static M error(Model model,String messageInfo) {
		M message = new M();
		model.addAttribute(MSG_KEY, message);
		message.status = STATUS_ERROR;
		message.message = messageInfo;
		return message;
	}

	/**
	 * 
	 * @method 返回结果
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param status
	 * @param message
	 * @return
	 */
	public M response(String status, String message) {
		this.status = status;
		this.message = message;
		return this;
	}

	/**
	 * 
	 * @method 创建成功
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param model
	 * @return
	 */
	public static M success(Model model) {
		M message = new M();
		model.addAttribute(MSG_KEY, message);
		message.status = STATUS_SUCCESS;
		message.message = "您的操作已成功!";
		return message;
	}

	/**
	 * 
	 * @method 创建成功
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @return
	 */
	public static M success() {
		M message = new M();
		message.status = STATUS_SUCCESS;
		message.message = "您的操作已成功!";
		return message;
	}

	/**
	 * 
	 * @method 设置响应状态
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param model
	 * @param status
	 * @return
	 */
	public static M status(Model model, String status) {
		M message = new M();
		model.addAttribute(MSG_KEY, message);
		message.status = status;
		return message;
	}

	/**
	 * 
	 * @method 创建成功
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param model
	 * @return
	 */
	public static M none(Model model) {
		M message = new M();
		model.addAttribute(MSG_KEY, message);
		return message;
	}

	/**
	 * 
	 * @method 返回页面地址
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @return
	 */
	public String go() {
		if (StringUtils.equals(STATUS_SUCCESS, status)) {
			return "common/success";
		} else {
			return "common/error";
		}
	}

	/**
	 * 
	 * @method forward到某个页面
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param url
	 * @return
	 */
	public String forward(String url) {
		return "forward:" + url;
	}

	/**
	 * 
	 * @method forward到某个页面
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param url
	 * @return
	 */
	public static String forwardTo(String url) {
		return "forward:" + url;
	}

	/**
	 * 
	 * @method render某个页面
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param url
	 * @return
	 */
	public static String goTo(String url) {
		return url;
	}

	/**
	 * 
	 * @method redirect到某个页面
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param url
	 * @return
	 */
	public static String redirectTo(String url) {
		return "redirect:" + url;
	}

	/**
	 * 
	 * @method 返回地址
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param url
	 * @return
	 */
	public String go(String url) {
		return url;
	}

	/**
	 * 
	 * @method 返回json格式消息
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @return
	 */
	public M ajax() {
		return this;
	}

	/**
	 * 
	 * @method 设置对象
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param object
	 * @return
	 */
	public M data(Object object) {
		this.data = object;
		return this;
	}
	/**
	 * 
	 * @method 将data设置为map
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @return
	 */
	public M data() {
		this.data = new HashMap<String, Object>();
		return this;
	}

	/**
	 * 
	 * @method 设置map的值
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public M set(String key, Object value) {
		if (data == null || (data instanceof Map) == false) {
			throw new IllegalArgumentException("调用此方法前请先调用M.map()方法!");
		}
		Map<String, Object> map = (Map<String, Object>) data;
		map.put(key, value);
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJsFunction() {
		return jsFunction;
	}

	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}
	/**
	 * 
	 * @method 输出json
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param response
	 * @param object
	 * @param contentType
	 */
	public static void ajax(HttpServletResponse response, Object object,String contentType){
		try {
			response.setContentType(contentType);
			String jsonString = JSON.toJSONString(object);
			PrintWriter writer = response.getWriter();
			writer.println(jsonString);
			writer.flush();
		} catch (IOException er) {
			log.error(er.getMessage(), er);
		}
	}
	/**
	 * 
	 * @method 输出Json
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param response
	 * @param object
	 */
	public static void ajax(HttpServletResponse response, Object object) {
		ajax(response,object,"text/html; charset=UTF-8");
	}

	public void ajaxM(HttpServletResponse response) {
		ajax(response, this);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 
	 * @method  跳转到外网
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param response
	 * @param url
	 * @return
	 */
	public static String forceRedirect(HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (Exception er) {
			log.debug(er.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @method 输出脚本
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param response
	 * @param script
	 * @return
	 */
	public static String script(HttpServletResponse response, String script){
		try {
			String template = "<script type=\"text/javascript\">%s</script>";
			script = String.format(template, script);
			response.reset();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(script);
			writer.flush();
		} catch (Exception er) {
			log.warn(er.getMessage(), er);
		}
		return null;
	}
	/**
	 * 
	 * @method 输出文本
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param response
	 * @param text
	 * @return
	 */
	public static String text(HttpServletResponse response,String text){
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(text);
			writer.flush();
		} catch (Exception er) {
			log.warn(er.getMessage(), er);
		}
		return null;
	}
}