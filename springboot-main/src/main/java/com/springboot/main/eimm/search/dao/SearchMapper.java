package com.springboot.main.eimm.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.role.entity.Role;
import com.springboot.main.eimm.search.entity.Search;

@Mapper
public interface SearchMapper extends BaseMapper<Search>{

	 
	 
	/**
	 * 
	 * @method 动态拼接sql，出入table表名获取list数据
	 * @author Mr yi
	 * @time 2019年5月23日
	 * @param columnMap
	 * @return
	 */
	@Select( "SELECT  * from ${table} t1 ")
	public List<Map<String,Object>> getList(Map<String, Object> columnMap);
	
	
}