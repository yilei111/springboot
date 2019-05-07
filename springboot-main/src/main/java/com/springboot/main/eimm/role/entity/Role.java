package com.springboot.main.eimm.role.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Entity(name = "t_sys_role")
@TableName(value = "t_sys_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "id不能为空")
	@Id
	@Column(name="id")
	private String id;
	
	@NotBlank(message = "角色名称不能为空")
	@Column(name="role_name")
	private String role_name;
	
	@NotEmpty(message = "角色代码不能为空")
	@Length(min = 6,max = 15, message = "角色代码长度为6-15位。")
	@Column(name="role_code")
	private String role_code;
	
	@NotBlank(message = "角色描述不能为空")
	@Column(name="role_desc")
	private String role_desc;
	
	@NotBlank(message = "角色状态不能为空")
	@Column(name="role_state")
	private String role_state;
	
	@Column(name="role_sort")
	private int role_sort;
	
	@Column(name="create_time")
	private Date  create_time;
	
	@Column(name="update_time")
	private Date  update_time;
	
	
	
}
