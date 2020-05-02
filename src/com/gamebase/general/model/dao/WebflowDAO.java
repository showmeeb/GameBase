package com.gamebase.general.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.Webflow;

@Repository
public class WebflowDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	public WebflowDAO() {
		
	}
	
	@Autowired
	public WebflowDAO(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Webflow insertIp(Webflow Webflow) {
		System.out.println("aa"+Webflow.getIp());
		sessionFactory.getCurrentSession().save(Webflow);				
		return Webflow;
	}
	
	public Webflow updateTimes(Webflow Webflow) {
		sessionFactory.getCurrentSession().save(Webflow);				
		return Webflow;
	}
}
