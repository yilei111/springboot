package com.springboot.main.eimm.permission.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.springboot.main.core.service.BaseService;
import com.springboot.main.eimm.permission.entity.Permission;

public interface PermissionService extends BaseService<Permission>{

	/**
	 * @method 查询用户具有的全部权限链接( permission_url 不为空的）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param user_id
	 * @return
	 */
	public List<Permission> getPermissionListByUserId(String user_id);
	 
}
