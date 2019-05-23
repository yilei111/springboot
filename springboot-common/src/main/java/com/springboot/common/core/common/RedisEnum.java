package com.springboot.common.core.common;

/**
 * 
 * @method 存放一些公共redis的常量key
 * @author Mr yi
 * @time 2019年5月10日
 */
public enum RedisEnum {
	
	//redis过期时间（秒）
	REDIS_EXPIRE_TIME("1800"),
	
	//用户菜单列表key
	REDIS_USER_MENU_LIST("user_menu_list"),
	//用户信息列表key
	REDIS_USER_LIST("user_list"); 
 	
	
	
	private String name; 
	

	private RedisEnum(String name ) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
  public static void main(String[] args) {
	  System.out.println( RedisEnum.REDIS_EXPIRE_TIME.getName());
}
	
}
