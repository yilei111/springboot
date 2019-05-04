package com.springboot.main.eimm.permission.entity;

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
public class Permission implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "id不能为空")
	private int id;
	@NotBlank(message = "权限名称不能为空")
	private String permission_name;
	@NotEmpty(message = "权限代码不能为空")
	@Length(min = 6,max = 15, message = "权限代码长度为6-15位。")
	private String permission_code;
	@NotBlank(message = "权限描述不能为空")
	private String permission_desc;
	@NotBlank(message = "权限类型不能为空")
	private String permission_type;
	@NotBlank(message = "权限状态不能为空")
	private String permission_state;
	@NotBlank(message = "权限父级id不能为空")
	private int parent_id;
	private int permission_sort;
	private Date  create_time;
	private Date  update_time;
	
	
	
}
