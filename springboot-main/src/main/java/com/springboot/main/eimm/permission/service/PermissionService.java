package com.springboot.main.eimm.permission.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.springboot.main.core.service.BaseService;
import com.springboot.main.eimm.permission.entity.Permission;

public interface PermissionService extends BaseService<Permission>{

	/**
	 * @method 查询用户具有的全部权限链接
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param user_id
	 * @return
	 */
	public List<Permission> getPermissionListByUserId(Map<String, Object> columnMap);
	 
	/**
	 * @method 查询角色具有的全部权限
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param Map<String, Object> columnMap
	 * @return
	 */
	public List<Permission> getPermissionListByRoleId(Map<String, Object> columnMap);
}
