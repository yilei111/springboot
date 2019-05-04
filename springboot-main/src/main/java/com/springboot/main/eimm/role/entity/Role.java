package com.springboot.main.eimm.role.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "id不能为空")
	private int id;
	@NotBlank(message = "角色名称不能为空")
	private String role_name;
	@NotEmpty(message = "角色代码不能为空")
	@Length(min = 6,max = 15, message = "角色代码长度为6-15位。")
	private String role_code;
	@NotBlank(message = "角色描述不能为空")
	private String role_desc;
	@NotBlank(message = "角色状态不能为空")
	private String role_state;
	private int role_sort;
	private Date  create_time;
	private Date  update_time;
	
	
	
}
