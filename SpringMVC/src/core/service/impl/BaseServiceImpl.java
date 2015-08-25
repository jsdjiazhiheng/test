package core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import core.dao.BaseDao;
import core.model.BaseEntity;
import core.service.BaseService;

@Service("baseService")
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>{

	@Autowired
	private BaseDao<T> baseDao;
	
	/**
	 * 保存数据
	 * @param t 实体
	 */
	@Transactional
	public Integer saveEntity(T t) {
		return baseDao.saveEntity(t);
	}
	
	/**
	 * 删除数据
	 * @param t 实体
	 */
	@Transactional
	public void removeEntity(T t) {
		baseDao.removeEntity(t);
	}
	
	/**
	 * 删除数据
	 * @param id 实体的id
	 */
	@Transactional
	public void romoveEntityByHql(String hql) {
		baseDao.romoveEntityByHql(hql);
	}
	
	/**
	 * 修改数据
	 * @param t 实体
	 */
	@Transactional
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}
	
	/**
	 * 加载数据
	 * @param id 实体的id
	 */
	public void loadEntity(Integer id) {
		baseDao.loadEntity(id);
	}
	
	/**
	 * 加载数据集合
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntity() {
		return baseDao.listEntity();
	}
	
	/**
	 * 加载数据集合
	 * @param hql 指定的hql语句
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntityByHql(String hql) {
		return baseDao.listEntityByHql(hql);
	}
	
	/**
	 * 加载数据集合
	 * @param sql 指定的sql语句
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntityBySql(String sql) {
		return baseDao.listEntityBySql(sql);
	}

	/**
	 * 加载数据集合
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntity() {
		return baseDao.listMapEntity();
	}
	
	/**
	 * 加载数据集合 
	 * @param hql 指定的hql语句
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntityByHql(String hql) {
		return baseDao.listMapEntityByHql(hql);
	}
	
	/**
	 * 加载数据集合 
	 * @param sql 指定的sql语句
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntityBySql(String sql) {
		return baseDao.listMapEntityBySql(sql);
	}

	/**
	 * 获取总长度
	 * @return 查询出来的长度
	 */
	public long countAll() {
		return baseDao.countAll();
	}
	
	/**
	 * 根据hql语句获取总长度
	 * @param hql hql语句条件
	 * @return 查询出来的长度
	 */
	public long countAllByHql(String hql) {
		return baseDao.countAllByHql(hql);
	}
	
	/**
	 * 根据sql语句获取总长度
	 * @param sql sql语句条件
	 * @return 查询出来的长度
	 */
	public long countAllBySql(String sql) {
		return baseDao.countAllBySql(sql);
	}
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

}
