package com.springboot.main.test.service.impl;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.test.dao.TestMapper;
import com.springboot.main.test.entity.Test;
import com.springboot.main.test.service.TestService;
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl  implements TestService {

	@Autowired
	TestMapper testMapper;

	@Override
	public Integer insert(Test entity) {
		// TODO Auto-generated method stub
		return testMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(Test entity) {
		// TODO Auto-generated method stub
		return testMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return testMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return testMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return testMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Test entity) {
		// TODO Auto-generated method stub
		return testMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Test entity) {
		// TODO Auto-generated method stub
		return testMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Test entity, Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public Test selectById(Serializable id) {
		// TODO Auto-generated method stub
		return testMapper.selectById(id);
	}

	@Override
	public List<Test> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return testMapper.selectBatchIds(idList);
	}

	@Override
	public List<Test> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return testMapper.selectByMap(columnMap);
	}

	@Override
	public Test selectOne(Test entity) {
		// TODO Auto-generated method stub
		return testMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.selectCount(wrapper);
	}

	@Override
	public List<Test> selectList(Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.selectObjs(wrapper);
	}

	@Override
	public List<Test> selectPage(RowBounds rowBounds, Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Test> wrapper) {
		// TODO Auto-generated method stub
		return testMapper.selectMapsPage(rowBounds, wrapper);
	}

	/**
	 * 获取全部数据
	 * @author Mr yi
	 * @return
	 */
	@Override
	public List<Test> getTestList() {
		return testMapper.getUserList();
	}
	
	

}
