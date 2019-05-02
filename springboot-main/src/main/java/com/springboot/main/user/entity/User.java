package com.springboot.main.user.entity;

import java.io.Serializable;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * user实体类
 * @author Mr yi
 */
@Data//生成get和set方法
@AllArgsConstructor//成包含所有带参的构造函数
@NoArgsConstructor//，加上这个注解就是会生成空构造函数
@Accessors(chain=true)//链式访问-可以user.setId(1).setName("zhangsan).这样访问属性
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @NotEmpty 用在集合类上面
	 * @NotBlank 用在String上面
	 * @NotNull 用在基本类型上
	 */
	@NotBlank(message = "id不能为空")
	private int id;
	@NotBlank(message = "用户名不能为空")
	private String name;
	@NotNull(message = "年龄不能为空")
	private int age;
	@AssertFalse(message = "必须为false")
	private Boolean isFalse;
	@Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确")
	private String birthday;

}
