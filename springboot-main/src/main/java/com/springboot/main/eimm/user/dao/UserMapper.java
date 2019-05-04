package com.springboot.main.eimm.user.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	
	
}