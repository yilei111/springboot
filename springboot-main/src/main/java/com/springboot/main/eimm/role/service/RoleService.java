package com.springboot.main.eimm.role.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.springboot.main.core.service.BaseService;
import com.springboot.main.eimm.role.entity.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * @method 查询用户具有的全部角色( role_state 为001启用）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param Map<String, Object> columnMap
	 * @return
	 */
	public List<Role> getRoleListByUserId(Map<String, Object> columnMaps);
}
