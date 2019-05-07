package com.springboot.main.core.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.springboot.main.eimm.user.entity.User;

/**
 * @method User对象密码MD5加密
 * @author Mr yi
 * @time 2019年5月6日
 */
public class PasswordHelper {
	
	//private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(User user) {
		//String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, user.getUser_pwd(),  ByteSource.Util.bytes(user.getUser_name()), hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
		user.setUser_pwd(newPassword);

	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		User user = new User();
		user.setUser_name("admin");
		user.setUser_pwd("admin");
		passwordHelper.encryptPassword(user);
		System.out.println(user);
	}
}
