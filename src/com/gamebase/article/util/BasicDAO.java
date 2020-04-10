package com.gamebase.article.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class BasicDAO<T> extends HibernateDaoSupport implements IBaseDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private T entityName;
	private Integer entityNameid;
	
	public  T  QueryDataById(){
		return getHibernateTemplate().get((Class<T>) entityName, entityNameid);
	}
	
	
	
	public T getEntityName() {
		return entityName;
	}
	public void setEntityName(T entityName) {
		this.entityName = entityName;
	}
	public Integer getEntityNameid() {
		return entityNameid;
	}
	public void setEntityNameid(Integer entityNameid) {
		this.entityNameid = entityNameid;
	}
	
}
