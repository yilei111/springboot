package com.springboot.main.eimm.user.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.user.dao.UserMapper;
import com.springboot.main.eimm.user.entity.User;
import com.springboot.main.eimm.user.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl   implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public Integer insert(User entity) {
		// TODO Auto-generated method stub
		return userMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(User entity) {
		// TODO Auto-generated method stub
		return userMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return userMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return userMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return userMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(User entity) {
		// TODO Auto-generated method stub
		return userMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(User entity) {
		// TODO Auto-generated method stub
		return userMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(User entity, Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public User selectById(Serializable id) {
		// TODO Auto-generated method stub
		return userMapper.selectById(id);
	}

	@Override
	public List<User> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return userMapper.selectBatchIds(idList);
	}

	@Override
	public List<User> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return userMapper.selectByMap(columnMap);
	}

	@Override
	public User selectOne(User entity) {
		// TODO Auto-generated method stub
		return userMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.selectCount(wrapper);
	}

	@Override
	public List<User> selectList(Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.selectObjs(wrapper);
	}

	@Override
	public List<User> selectPage(RowBounds rowBounds, Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<User> wrapper) {
		// TODO Auto-generated method stub
		return userMapper.selectMapsPage(rowBounds, wrapper);
	}
	

}
