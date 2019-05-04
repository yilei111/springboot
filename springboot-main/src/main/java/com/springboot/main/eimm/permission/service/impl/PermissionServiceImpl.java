package com.springboot.main.eimm.permission.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.permission.dao.PermissionMapper;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.permission.service.PermissionService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl   implements PermissionService {

	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public Integer insert(Permission entity) {
		// TODO Auto-generated method stub
		return permissionMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(Permission entity) {
		// TODO Auto-generated method stub
		return permissionMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return permissionMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return permissionMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return permissionMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Permission entity) {
		// TODO Auto-generated method stub
		return permissionMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Permission entity) {
		// TODO Auto-generated method stub
		return permissionMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Permission entity, Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public Permission selectById(Serializable id) {
		// TODO Auto-generated method stub
		return permissionMapper.selectById(id);
	}

	@Override
	public List<Permission> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return permissionMapper.selectBatchIds(idList);
	}

	@Override
	public List<Permission> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return permissionMapper.selectByMap(columnMap);
	}

	@Override
	public Permission selectOne(Permission entity) {
		// TODO Auto-generated method stub
		return permissionMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.selectCount(wrapper);
	}

	@Override
	public List<Permission> selectList(Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.selectObjs(wrapper);
	}

	@Override
	public List<Permission> selectPage(RowBounds rowBounds, Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Permission> wrapper) {
		// TODO Auto-generated method stub
		return permissionMapper.selectMapsPage(rowBounds, wrapper);
	}
	

}
