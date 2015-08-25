package core.service;

import java.util.List;
import java.util.Map;

import core.model.BaseEntity;

public interface BaseService<T extends BaseEntity> {
	/**
	 * 保存数据
	 * @param t 实体
	 */
	public Integer saveEntity(T t);
	
	/**
	 * 删除数据
	 * @param t 实体
	 */
	public void removeEntity(T t);
	
	/**
	 * 删除数据
	 * @param id 实体的id
	 */
	public void romoveEntityByHql(String hql);
	
	/**
	 * 修改数据
	 * @param t 实体
	 */
	public void updateEntity(T t);
	
	/**
	 * 加载数据
	 * @param id 实体的id
	 */
	public void loadEntity(Integer id);
	
	/**
	 * 加载数据集合
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntity();
	
	/**
	 * 加载数据集合
	 * @param hql 指定的hql语句
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntityByHql(String hql);
	
	/**
	 * 加载数据集合
	 * @param sql 指定的sql语句
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntityBySql(String sql);
	
	/**
	 * 加载数据集合
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntity();
	
	/**
	 * 根据 hql 加载数据集合
	 * @param hql
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntityByHql(String hql);
	
	/**
	 * 根据 sql 加载数据集合
	 * @param sql
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntityBySql(String sql);
	
	/**
	 * 获取总长度
	 * @return 查询出来的长度
	 */
	public long countAll();
	
	/**
	 * 根据hql语句获取总长度
	 * @param hql hql语句条件
	 * @return 查询出来的长度
	 */
	public long countAllByHql(String hql);
	
	/**
	 * 根据sql语句获取总长度
	 * @param sql sql语句条件
	 * @return 查询出来的长度
	 */
	public long countAllBySql(String sql);
	
}
