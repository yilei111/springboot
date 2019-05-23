package com.springboot.main.function.article.entity;

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
@Entity(name = "t_function_article")
@TableName(value = "t_function_article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	// id
	@Id
	@Column(name = "id")
	private String id;
	// 文章标题
	@Column(name = "artitle_title")
	private String artitle_title;
	// 文章内容
	@Column(name = "artitle_content")
	private String artitle_content;
	// 文章外链接
	@Column(name = "artitle_url")
	private String artitle_url;
	// 文字发布作者
	@Column(name = "artitle_author")
	private String artitle_author;
	// 文章类型
	@Column(name = "artitle_type")
	private String artitle_type;
	// 文章状态
	@Column(name = "artitle_state")
	private String artitle_state;
	@Column(name = "create_time")
	private Date create_time;
	@Column(name = "update_time")
	private Date update_time;

}