package com.springboot.main.eimm.user.service;

import java.util.List;
import java.util.Map;

import com.springboot.main.core.service.BaseService;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.user.entity.User;

public interface UserService extends BaseService<User>{

	/**
	 * @method 根据Map参数值，来查询用户对象（如果map为空，返回null对象）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param map
	 * @return
	 */
	public User selectUserByUserName(Map<String,Object> map);
	
	/**
	 * 
	 * @method 查询用户具有的全部权限
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param columnMap
	 * @return
	 */
	public List<Permission> getPermissionListByUserId(Map<String, Object> columnMap);
}
