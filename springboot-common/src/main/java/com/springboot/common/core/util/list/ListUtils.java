package com.springboot.common.core.util.list;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @method 提供对List集合操作的工具类方法
 * @author Mr yi
 * @time 2019年5月10日
 */
public final class ListUtils<T> {

	/**
	 * 
	 * @method 对list的集合进行去重
	 * @author Mr yi
	 * @time 2019年5月10日
	 * @param list
	 * @return
	 */
	public static List<?> listDistinct(List<?> list) {
		return list.stream().distinct().collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @method 对list集合按照某一字段属性进行排序
	 * @author Mr yi
	 * @time 2019年5月10日	
	 * @param targetList 需要排序的list
	 * @param sortField	需要排序的字段
	 * @param sortMode	需要排序的方式desc-asc
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void  sort(List<T> targetList, final String sortField, final String sortMode) {
	
		Collections.sort(targetList, new Comparator() {
			public int compare(Object obj1, Object obj2) { 
				int retVal = 0;
				try {
					//首字母转大写
					String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w",""); 
					String methodStr="get"+newStr;
					
					Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);
					Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);
					if (sortMode != null && "desc".equals(sortMode)) {
						retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序
					} else {
						retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序
					}
				} catch (Exception e) {
					throw new RuntimeException();
				}
				return retVal;
			}
		});
	}
 
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("b");
		list.add("a");
		list.add("a");
		System.out.println(ListUtils.listDistinct(list));
	}
}
