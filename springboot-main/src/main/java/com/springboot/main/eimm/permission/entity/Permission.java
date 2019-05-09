package com.springboot.main.eimm.permission.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Entity(name = "t_sys_permission")
@TableName(value = "t_sys_permission")
public class Permission  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "id不能为空")
	@Id
	@Column(name="id")
	private String id;
	
	@NotBlank(message = "权限名称不能为空")
	@Column(name="permission_name")
	private String permission_name;
	
	@NotBlank(message = "权限链接不能为空")
	@Column(name="permission_url")
	private String permission_url;
	
	@NotBlank(message = "权限模块不能为空")
	@Column(name="permission_module")
	private String permission_module;
	
	@NotEmpty(message = "权限代码不能为空")
	@Length(min = 6,max = 15, message = "权限代码长度为6-15位。")
	@Column(name="permission_code")
	private String permission_code;
	
	@NotBlank(message = "权限描述不能为空")
	@Column(name="permission_desc")
	private String permission_desc;
	
	@NotBlank(message = "权限类型不能为空")
	@Column(name="permission_type")
	private String permission_type;
	
	@NotBlank(message = "权限状态不能为空")
	@Column(name="permission_state")
	private String permission_state;
	
	@NotBlank(message = "权限父级id不能为空")
	@Column(name="parent_id")
	private int parent_id;
	
	@Column(name="permission_sort")
	private int permission_sort;
	
	@Column(name="create_time")
	private Date  create_time;
	
	@Column(name="update_time")
	private Date  update_time;
	
	
	
}
