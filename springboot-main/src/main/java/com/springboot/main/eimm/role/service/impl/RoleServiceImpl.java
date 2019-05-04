package com.springboot.main.eimm.role.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.role.dao.RoleMapper;
import com.springboot.main.eimm.role.entity.Role;
import com.springboot.main.eimm.role.service.RoleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl   implements RoleService {

	@Autowired
	RoleMapper roleMapper;

	@Override
	public Integer insert(Role entity) {
		// TODO Auto-generated method stub
		return roleMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(Role entity) {
		// TODO Auto-generated method stub
		return roleMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return roleMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Role entity) {
		// TODO Auto-generated method stub
		return roleMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Role entity) {
		// TODO Auto-generated method stub
		return roleMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Role entity, Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public Role selectById(Serializable id) {
		// TODO Auto-generated method stub
		return roleMapper.selectById(id);
	}

	@Override
	public List<Role> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return roleMapper.selectBatchIds(idList);
	}

	@Override
	public List<Role> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return roleMapper.selectByMap(columnMap);
	}

	@Override
	public Role selectOne(Role entity) {
		// TODO Auto-generated method stub
		return roleMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.selectCount(wrapper);
	}

	@Override
	public List<Role> selectList(Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.selectObjs(wrapper);
	}

	@Override
	public List<Role> selectPage(RowBounds rowBounds, Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Role> wrapper) {
		// TODO Auto-generated method stub
		return roleMapper.selectMapsPage(rowBounds, wrapper);
	}
	

}
