package com.springboot.main.function.article.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.function.article.dao.ArticleMapper;
import com.springboot.main.function.article.entity.Article;
import com.springboot.main.function.article.service.ArticleService;
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;
	
	@Override
	public Integer insert(Article entity) {
		// TODO Auto-generated method stub
		return articleMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(Article entity) {
		// TODO Auto-generated method stub
		return articleMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return articleMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return articleMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Article entity) {
		// TODO Auto-generated method stub
		return articleMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Article entity) {
		// TODO Auto-generated method stub
		return articleMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Article entity, Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.update(entity, wrapper);
	}

	@Override
	public Integer updateForSet(String setStr, Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.updateForSet(setStr, wrapper);
	}

	@Override
	public Article selectById(Serializable id) {
		// TODO Auto-generated method stub
		return articleMapper.selectById(id);
	}

	@Override
	public List<Article> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return articleMapper.selectBatchIds(idList);
	}

	@Override
	public List<Article> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return articleMapper.selectByMap(columnMap);
	}

	@Override
	public Article selectOne(Article entity) {
		// TODO Auto-generated method stub
		return articleMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.selectCount(wrapper);
	}

	@Override
	public List<Article> selectList(Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.selectObjs(wrapper);
	}

	@Override
	public List<Article> selectPage(RowBounds rowBounds, Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Article> wrapper) {
		// TODO Auto-generated method stub
		return articleMapper.selectMapsPage(rowBounds, wrapper);
	}

}
