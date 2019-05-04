package com.springboot.main.test.service;

import java.util.List;

import com.springboot.core.service.BaseService;
import com.springboot.main.test.entity.Test;
/**
 * 用户Test service接口
 * @author Mr yi
 *
 */
public interface TestService extends BaseService<Test>{

	/**
	 * 获取全部数据
	 * @author Mr yi
	 * @return
	 */
	List<Test> getTestList();
}
