package com.springboot.main.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * @method Service 通用接口方法
 * @author Mr yi
 * @time 2019年5月6日
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * <p>
	 * 插入一条记录
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return int
	 */
	Integer insert(T entity);

	/**
	 * <p>
	 * 插入一条记录
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return int
	 */
	Integer insertAllColumn(T entity);

	/**
	 * <p>
	 * 根据 ID 删除
	 * </p>
	 *
	 * @param id 主键ID
	 * @return int
	 */
	Integer deleteById(Serializable id);

	/**
	 * <p>
	 * 根据 columnMap 条件，删除记录
	 * </p>
	 *
	 * @param columnMap 表字段 map 对象
	 * @return int
	 */
	Integer deleteByMap(@Param("cm") Map<String, Object> columnMap);

	/**
	 * <p>
	 * 根据 entity 条件，删除记录
	 * </p>
	 *
	 * @param wrapper 实体对象封装操作类（可以为 null）
	 * @return int
	 */
	Integer delete(@Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 删除（根据ID 批量删除）
	 * </p>
	 *
	 * @param idList 主键ID列表
	 * @return int
	 */
	Integer deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

	/**
	 * <p>
	 * 根据 ID 修改
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return int
	 */
	Integer updateById(@Param("et") T entity);

	/**
	 * <p>
	 * 根据 ID 修改
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return int
	 */
	Integer updateAllColumnById(@Param("et") T entity);

	/**
	 * <p>
	 * 根据 whereEntity 条件，更新记录
	 * </p>
	 *
	 * @param entity  实体对象
	 * @param wrapper 实体对象封装操作类（可以为 null）
	 * @return
	 */
	Integer update(@Param("et") T entity, @Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 whereEntity 条件，更新记录
	 * </p>
	 *
	 * @param setStr  set字符串
	 * @param wrapper 实体对象封装操作类（可以为 null）
	 * @return
	 */
	Integer updateForSet(@Param("setStr") String setStr, @Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 ID 查询
	 * </p>
	 *
	 * @param id 主键ID
	 * @return T
	 */
	T selectById(Serializable id);

	/**
	 * <p>
	 * 查询（根据ID 批量查询）
	 * </p>
	 *
	 * @param idList 主键ID列表
	 * @return List<T>
	 */
	List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList);

	/**
	 * <p>
	 * 查询（根据 columnMap 条件）
	 * </p>
	 *
	 * @param columnMap 表字段 map 对象
	 * @return List<T>
	 */
	List<T> selectByMap(@Param("cm") Map<String, Object> columnMap);

	/**
	 * <p>
	 * 根据 entity 条件，查询一条记录
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return T
	 */
	T selectOne(@Param("ew") T entity);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询总记录数
	 * </p>
	 *
	 * @param wrapper 实体对象
	 * @return int
	 */
	Integer selectCount(@Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 entity 条件，查询全部记录
	 * </p>
	 *
	 * @param wrapper 实体对象封装操作类（可以为 null）
	 * @return List<T>
	 */
	List<T> selectList(@Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询全部记录
	 * </p>
	 *
	 * @param wrapper 实体对象封装操作类（可以为 null）
	 * @return List<T>
	 */
	List<Map<String, Object>> selectMaps(@Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询全部记录 注意： 只返回第一个字段的值
	 * </p>
	 *
	 * @param wrapper 实体对象封装操作类（可以为 null）
	 * @return List<Object>
	 */
	List<Object> selectObjs(@Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 entity 条件，查询全部记录（并翻页）
	 * </p>
	 *
	 * @param rowBounds 分页查询条件（可以为 RowBounds.DEFAULT）
	 * @param wrapper   实体对象封装操作类（可以为 null）
	 * @return List<T>
	 */
	List<T> selectPage(RowBounds rowBounds, @Param("ew") Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询全部记录（并翻页）
	 * </p>
	 *
	 * @param rowBounds 分页查询条件（可以为 RowBounds.DEFAULT）
	 * @param wrapper   实体对象封装操作类
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, @Param("ew") Wrapper<T> wrapper);

}
