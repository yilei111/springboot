package com.springboot.main.eimm.role.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hibernate.annotations.Cache;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.role.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role>{

	
	/**
	 * @method 查询用户具有的全部角色
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param Map<String, Object> columnMap
	 * @return
	 */
	@Select({"<script>",
				"select  t1.*  from t_sys_role t1 where 1=1 ",
				"<when test='role_state!=null'>",
					"and t1.role_state= #{role_state}",
				"</when>",
				"and    exists (SELECT role_id from  t_sys_user_role t2",
				"where t2.user_id=#{user_id} and t2.role_id=t1.id)",
			" </script>"})
	public List<Role> getRoleListByUserId(Map<String, Object> columnMap);
	
}