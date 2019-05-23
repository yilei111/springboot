package com.springboot.main.function.file.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.role.entity.Role;
import com.springboot.main.function.article.entity.Article;
import com.springboot.main.function.file.entity.File;

@Mapper
public interface FileMapper extends BaseMapper<File>{

	 
}