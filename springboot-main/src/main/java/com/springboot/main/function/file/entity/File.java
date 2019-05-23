package com.springboot.main.function.file.entity;

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
@Accessors(chain = true)
@Entity(name = "t_function_file")
@TableName(value = "t_function_file")
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	//id
	@Id
	@Column(name = "id")
	private String id;
	// 文件标题
	@Column(name = "file_title")
	private String file_title;
	// 文件地址
	@Column(name = "file_url")
	private String file_url;
	// 上传作者
	@Column(name = "file_author")
	private String file_author;
	// 文件类型
	@Column(name = "file_type")
	private String file_type;
	// 文件状态
	@Column(name = "file_state")
	private String file_state;
	@Column(name = "create_time")
	private Date create_time;
	@Column(name = "update_time")
	private Date update_time;

}
