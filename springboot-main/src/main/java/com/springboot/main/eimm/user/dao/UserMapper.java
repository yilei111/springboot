package com.springboot.main.eimm.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.eimm.user.entity.User;
import com.springboot.main.test.entity.Test;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}