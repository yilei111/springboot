package com.springboot.main.function.file.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.function.file.service.FileService;
import com.springboot.main.core.service.BaseService;
import com.springboot.main.core.service.BaseService2;
import com.springboot.main.function.file.dao.FileMapper;
import com.springboot.main.function.file.entity.File;
 
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl  implements  FileService {

	@Autowired
	FileMapper fileMapper;

	@Override
	public Integer insert(File entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertAllColumn(File entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateById(File entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAllColumnById(File entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(File entity, Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File selectOne(File entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer selectCount(Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> selectList(Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> selectObjs(Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> selectPage(RowBounds rowBounds, Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<File> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}
