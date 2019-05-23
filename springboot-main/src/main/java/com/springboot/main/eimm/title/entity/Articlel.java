package com.springboot.main.eimm.title.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Entity(name = "t_function_article1")
@TableName(value = "t_function_article1")
public class Articlel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//新闻
	@Id
	@Column(name="id")
	private String id;
	//文章标题
	@Column(name="title")
	private String title;
	//文章内容
	@Column(name="content")
	private String content;
	//文章链接
	@Column(name="url")
	private String url;
	//文章的时间
	@Column(name="date")
	private String date;
	//文章的作者
	@Column(name="author")
	private String author;
 
}

