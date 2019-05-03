package com.springboot.main.user.service;

import java.util.List;
import com.springboot.core.BaseService;
import com.springboot.main.user.entity.User;
/**
 * 用户user service接口
 * @author Mr yi
 *
 */
public interface UserService extends BaseService<User>{

	/**
	 * 获取全部数据
	 * @author Mr yi
	 * @return
	 */
	List<User> getUserList();
}
