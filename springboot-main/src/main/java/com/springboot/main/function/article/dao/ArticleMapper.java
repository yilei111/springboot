package com.springboot.main.function.article.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.role.entity.Role;
import com.springboot.main.function.article.entity.Article;

@Mapper
public interface ArticleMapper extends BaseMapper<Article>{

	 
	
}