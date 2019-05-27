package com.springboot.main.eimm.search.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "t_sys_search_index")
@TableName(value = "t_sys_search_index")
/**
 * 
 * @method 一表多用（表示索引数据对象和需要添加索引的数据库表字段对应关系）
 * @author Mr yi
 * @time 2019年5月23日
 */
public class Search implements Serializable {

	private static final long serialVersionUID = 1L;
	//id
	@Id
	@Column(name = "id")
	private String id;
	//标题
	@Column(name = "title")
	private String title;
	//内容
	@Column(name = "content")
	private String content;
	//发布作者
	@Column(name = "author")
	private String author;
	//所属类型
	@Column(name = "type")
	private String type;
	@Column(name = "create_time")
	private String create_time;

}