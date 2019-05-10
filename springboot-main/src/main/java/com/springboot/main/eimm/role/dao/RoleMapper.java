package com.springboot.main.eimm.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.role.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role>{

	
	/**
	 * @method 查询用户具有的全部角色( role_state 为001启用）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param user_id
	 * @return
	 */
	@Select("SELECT  t1.*  from t_sys_role t1 where t1.role_state='001' and    exists (SELECT role_id from  t_sys_user_role t2 where t2.user_id=#{user_id} and t2.role_id=t1.id)")
	public List<Role> getRoleListByUserId(String user_id);
	
}