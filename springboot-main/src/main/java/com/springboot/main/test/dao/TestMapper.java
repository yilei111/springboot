package com.springboot.main.test.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.main.test.entity.Test;

/**
 * mybatis plus 配置
 * @author mr yi
 */
@Mapper
public interface TestMapper extends BaseMapper<Test>{

	/*
	 * @Delete("drop table if exists user") void dropTable();
	 * 
	 * @Insert("CREATE TABLE IF NOT EXISTS user(id INT UNSIGNED AUTO_INCREMENT,name VARCHAR(100) NOT NULL,"
	 * + "  age INT NOT NULL,PRIMARY KEY (id)" +
	 * ")ENGINE=InnoDB DEFAULT CHARSET=utf8;") void createTable();
	 * 
	 * @Insert("insert into user(name,age) values(#{name},#{age})") void insert(User
	 * user);
	 * 
	 * @Select("select id,name,age from user") List<User> findAll();
	 */
	/*
	 * @Select("SELECT * FROM user") Page<User> getUserList();
	 */
	@Select("SELECT * from user")
	List<Test> getUserList();
	
}