package com.springboot.main.eimm.rolePermission.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.main.eimm.rolePermission.service.RolePermissionService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.rolePermission.dao.RolePermissionMapper;
import com.springboot.main.eimm.rolePermission.entity.RolePermission;
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl   implements RolePermissionService {

	@Autowired
	RolePermissionMapper RolePermissionMapper;

	@Override
	public Integer insert(RolePermission entity) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(RolePermission entity) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(RolePermission entity) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(RolePermission entity) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(RolePermission entity, Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public RolePermission selectById(Serializable id) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectById(id);
	}

	@Override
	public List<RolePermission> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectBatchIds(idList);
	}

	@Override
	public List<RolePermission> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectByMap(columnMap);
	}

	@Override
	public RolePermission selectOne(RolePermission entity) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectCount(wrapper);
	}

	@Override
	public List<RolePermission> selectList(Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectObjs(wrapper);
	}

	@Override
	public List<RolePermission> selectPage(RowBounds rowBounds, Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<RolePermission> wrapper) {
		// TODO Auto-generated method stub
		return RolePermissionMapper.selectMapsPage(rowBounds, wrapper);
	}
	

}
