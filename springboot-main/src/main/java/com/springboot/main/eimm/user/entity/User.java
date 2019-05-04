package com.springboot.main.eimm.user.entity;

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
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "id不能为空")
	private int id;
	@NotBlank(message = "用户名不能为空")
	private String user_name;
	@NotEmpty(message = "密码不能为空")
	@Length(min = 6, max = 8, message = "密码长度为6-8位。")
	@Pattern(regexp = "[a-zA-Z]*", message = "密码不合法")
	private String user_pwd;
	@NotBlank(message = "性别不能为空")
	private String user_sex;
	@NotNull(message = "年龄不能为空")
	@Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
	private int user_age;
	@NotBlank(message = "民族不能为空")
	private String user_national;
	@NotBlank(message = "手机号不能为空")
	private String user_phone;
	@Email
	private String user_email;
	@NotBlank(message = "状态不能为空")
	private String user_state;
	@NotBlank(message = "地址不能为空")
	private String user_address;
	@Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="出生日期格式不正确")
    private String user_birthday;
	private Date  create_time;
	private Date  update_time;
	
	
	
}
