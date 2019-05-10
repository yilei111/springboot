package com.springboot.common.core.util.uuid;
import java.util.UUID;

/**
 * @method 提供随机获取UUID字符串(无中划线)
 * @author Mr yi
 * @time 2019年5月10日
 */
public abstract class CommonUtils {

	/**
	 * @method 随机获取UUID字符串(无中划线)
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @returnUUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}
	

	
}
