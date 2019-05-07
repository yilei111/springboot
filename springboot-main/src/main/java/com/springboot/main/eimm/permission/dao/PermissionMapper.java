package com.springboot.main.eimm.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.permission.entity.Permission;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission>{

	/**
	 * @method 查询用户具有的全部权限链接( permission_url 不为空的）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param user_id
	 * @return
	 */
	@Select("SELECT  DISTINCT t1.* from t_sys_permission t1 INNER JOIN t_sys_role_permission t2 on t1.id=t2.permission_id INNER JOIN t_sys_user_role t3 on t2.role_id=t3.role_id and t3.user_id=#{user_id} and permission_url !='' ")
	public List<Permission> getPermissionListByUserId(String user_id);
}