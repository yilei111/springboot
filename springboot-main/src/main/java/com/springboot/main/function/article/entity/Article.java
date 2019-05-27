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
	@Column(name = "article_title")
	private String article_title;
	// 文章内容
	@Column(name = "article_content")
	private String article_content;
	// 文章外链接
	@Column(name = "article_url")
	private String article_url;
	// 文字发布作者
	@Column(name = "article_author")
	private String article_author;
	// 文章类型
	@Column(name = "article_type")
	private String article_type;
	// 文章状态
	@Column(name = "article_state")
	private String article_state;
	@Column(name = "create_time")
	private Date create_time;
	@Column(name = "update_time")
	private Date update_time;

}