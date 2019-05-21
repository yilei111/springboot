package com.springboot.main.eimm.permission.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.role.entity.Role;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission>{

	/**
	 * @method 查询用户具有的全部权限链接( permission_url 不为空的）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param user_id
	 * @return
	 */
	//@Select("SELECT  DISTINCT t1.* from t_sys_permission t1 INNER JOIN t_sys_role_permission t2 on t1.id=t2.permission_id INNER JOIN t_sys_user_role t3 on t2.role_id=t3.role_id and t3.user_id=#{user_id} and permission_url !='' ")
	
	@Select({"<script>",
		    	"SELECT DISTINCT t1.* from t_sys_permission t1 ",
		    	"INNER JOIN t_sys_role_permission t2 on t1.id=t2.permission_id",
		    	"INNER JOIN t_sys_role t3 on t2.role_id=t3.id",
		    	//角色状态判断
		    	"<when test='role_state!=null'>", 
					"and t3.role_state= #{role_state}", 
				"</when>",
				//用户id
		    	"INNER JOIN t_sys_user_role t4 on t3.id=t4.role_id and t4.user_id=#{user_id} where 1=1 ",
		    	//权限状态判断
		    	"<when test='permission_state!=null'>", 
					"and t1.permission_state= #{permission_state}", 
				"</when>",
				//权限类型判断
		    	"<when test='permission_type!=null'>", 
					"and t1.permission_type= #{permission_type}", 
				"</when>",
				//权限父级iD判断
		    	"<when test='parent_id!=null'>", 

			    	"<when test='parent_id==0'>", 
						"and t1.parent_id= 0", 
					"</when>",
					"<when test='parent_id!=0 '>", 
						"and t1.parent_id!= 0", 
					"</when>",
		    	
				"</when>",
				 
		    	"ORDER BY t1.permission_sort asc ",
			" </script>"})
	public List<Permission> getPermissionListByUserId(Map<String, Object> columnMap);
	
	/**
	 * @method 查询角色具有的全部权限
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param Map<String, Object> columnMap
	 * @return
	 */
	@Select({"<script>",
				"select  t1.*  from t_sys_permission t1 where 1=1 ",
				"<when test='permission_state!=null'>",
					"and t1.permission_state= #{permission_state}",
				"</when>",
				"and    exists (SELECT permission_id from  t_sys_role_permission t2",
				"where t2.role_id=#{role_id} and t2.permission_id=t1.id)",
			" </script>"})
	public List<Permission> getPermissionListByRoleId(Map<String, Object> columnMap);
	
}