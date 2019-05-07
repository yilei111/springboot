package com.springboot.main.eimm.userRole.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.main.eimm.userRole.service.UserRoleService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.userRole.dao.UserRoleMapper;
import com.springboot.main.eimm.userRole.entity.UserRole;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl   implements UserRoleService {

	@Autowired
	UserRoleMapper UserRoleMapper;

	@Override
	public Integer insert(UserRole entity) {
		// TODO Auto-generated method stub
		return UserRoleMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(UserRole entity) {
		// TODO Auto-generated method stub
		return UserRoleMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return UserRoleMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return UserRoleMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return UserRoleMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(UserRole entity) {
		// TODO Auto-generated method stub
		return UserRoleMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(UserRole entity) {
		// TODO Auto-generated method stub
		return UserRoleMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(UserRole entity, Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public UserRole selectById(Serializable id) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectById(id);
	}

	@Override
	public List<UserRole> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectBatchIds(idList);
	}

	@Override
	public List<UserRole> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectByMap(columnMap);
	}

	@Override
	public UserRole selectOne(UserRole entity) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectCount(wrapper);
	}

	@Override
	public List<UserRole> selectList(Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectObjs(wrapper);
	}

	@Override
	public List<UserRole> selectPage(RowBounds rowBounds, Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<UserRole> wrapper) {
		// TODO Auto-generated method stub
		return UserRoleMapper.selectMapsPage(rowBounds, wrapper);
	}
	

}
