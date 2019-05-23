package com.springboot.main.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.permission.entity.Permission;

/**
 * @method Service 通用接口方法
 * @author Mr yi
 * @time 2019年5月6日
 * @param <T>
 */
public class BaseService2<T> implements BaseMapper<T>{

	@Autowired
	BaseMapper<T> baseMapper;
	
	@Override
	public Integer insert(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return baseMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return baseMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return baseMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(T entity, Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public T selectById(Serializable id) {
		// TODO Auto-generated method stub
		return baseMapper.selectById(id);
	}

	@Override
	public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return baseMapper.selectBatchIds(idList);
	}

	@Override
	public List<T> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return baseMapper.selectByMap(columnMap);
	}

	@Override
	public T selectOne(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectCount(wrapper);
	}

	@Override
	public List<T> selectList(Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectObjs(wrapper);
	}

	@Override
	public List<T> selectPage(RowBounds rowBounds, Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<T> wrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectMapsPage(rowBounds, wrapper);
	}

	 

}
