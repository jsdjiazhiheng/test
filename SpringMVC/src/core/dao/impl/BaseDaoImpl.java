package core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import core.dao.BaseDao;
import core.model.BaseEntity;

/**
 * 公共的Dao层接口实现类
 * @author jiazh
 * @param <T> 泛型，指定实体类型
 */
@Repository("baseDao")
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;
	protected Class<?> entityClass;
	/**
	 * 获得当前事物的session
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 获取泛型的Class
	 * @return
	 */
	public Class getClas(){
		Class c = this.getClass();
        System.out.println(c);
        Type t = c.getGenericSuperclass();
        System.out.println(t);
        if (t instanceof ParameterizedType) {
            System.out.println("in if");
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
        return entityClass;
	}
	
	/**
	 * 保存数据
	 * @param t 实体
	 */
	public Integer saveEntity(T t) {
		if(t != null) {
			return (Integer)this.getCurrentSession().save(t);
		}
		return null;
	}
	
	/**
	 * 删除数据
	 * @param t 实体
	 */
	public void removeEntity(T t) {
		if(t != null) {
			this.getCurrentSession().delete(t);
		}
	}
	
	/**
	 * 删除数据
	 * @param id 实体的id
	 */
	public void romoveEntityByHql(String sql) {
		this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * 修改数据
	 * @param t 实体
	 */
	public void updateEntity(T t) {
		if(t != null){
			this.getCurrentSession().update(t);
		}
	}
	
	/**
	 * 加载数据
	 * @param id 实体的id
	 */
	public void loadEntity(Integer id) {
		if(id != null){
			this.getCurrentSession().get(getClas(), id);
		}
	}

	/**
	 * 加载数据集合
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntity() {
		
		return listEntityByHql(null);
	}

	/**
	 * 加载数据集合
	 * @param hql 指定的hql语句
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntityByHql(String hql) {
		return this.getCurrentSession().createQuery(sortHql(hql)).list();
	}

	/**
	 * 加载数据集合
	 * @param sql 指定的sql语句
	 * @return 查询出的数据集合,返回为该实体的集合
	 */
	public List<T> listEntityBySql(String sql) {
		return this.getCurrentSession().createSQLQuery(sortSql("")).list();
	}

	/**
	 * 加载数据集合
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntity() {
		return this.getCurrentSession().createQuery(sortHql("")).list();
	}
	
	/**
	 * 根据 hql 加载数据集合
	 * @param hql
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntityByHql(String hql) {
		return this.getCurrentSession().createQuery(sortHql(hql)).list();
	}
	
	/**
	 * 根据 sql 加载数据集合
	 * @param sql
	 * @return 查询出的数据集合,返回为 List< Map < String, Object > > 类型的集合
	 */
	public List<Map<String, Object>> listMapEntityBySql(String sql) {
		return this.getCurrentSession().createSQLQuery(sortSql(sql)).list();
	}
	
	/**
	 * 获取总长度
	 * @return 查询出来的长度
	 */
	public long countAll() {
		return (Long)this.getCurrentSession().createQuery(sortHql("")).uniqueResult();
	}
	
	/**
	 * 根据hql语句获取总长度
	 * @param hql hql语句条件
	 * @return 查询出来的长度
	 */
	public long countAllByHql(String hql) {
		return (Long)this.getCurrentSession().createQuery(sortHql(hql)).uniqueResult();
	}
	
	/**
	 * 根据sql语句获取总长度
	 * @param sql sql语句条件
	 * @return 查询出来的长度
	 */
	public long countAllBySql(String sql) {
		return (Long)this.getCurrentSession().createSQLQuery(sortSql(sql)).uniqueResult();
	}
	
	private String sortHql(String hql){
		if(hql == null || hql==""){
			hql = "from " + getClas().getName();
		}
		hql += " order by sUuid asc";
		return hql;
	}
	
	private String sortSql(String sql){
		sql +="  order by sUuid asc";
		return sql;
	}
	
}
