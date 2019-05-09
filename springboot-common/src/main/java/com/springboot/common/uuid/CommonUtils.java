package com.springboot.common.uuid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;
/**
 * 所属类别:工具类
 * 用途:提供随机获取UUID字符串(无中划线)
 * @author yilei
 * version:1.0
 */
public abstract class CommonUtils {

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}
	

	
}
