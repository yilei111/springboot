package com.springboot.main.eimm.search.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.search.entity.Search;
import com.springboot.main.eimm.title.entity.Articlel;

public interface SearchService {

	/**
	 * 
	 * @method 添加索引(首次启动第一次添加索引，会对全部数据进行第一次索引添加，后续不再执行此方法，只会添加、更新或删除索引）
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void addIndex() throws Exception;

	/**
	 * 	
	 * @method 根据表名获取Search对象信息
	 * @author Mr yi
	 * @time 2019年5月23日
	 * @param table 表名（小写）
	 * @return
	 */
	public  Search getSearchByTable(String table);
}
