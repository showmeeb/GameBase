package com.gamebase.general.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.Webflow;
import com.gamebase.member.model.UserProfile;

@Repository
public class WebflowDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public WebflowDAO() {

	}

	@Autowired
	public WebflowDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//插入
	public Webflow insertIp(Webflow webflow) {
		System.out.println("aa" + webflow.getIp());
		sessionFactory.getCurrentSession().save(webflow);
		return webflow;
	}
	//查詢今天有沒有來過
	public Webflow queryByIpNDate(String ip, String date) {
		Query<Webflow> query = sessionFactory.getCurrentSession()
				.createQuery("from Webflow Where ip= :ip and logdate = :date", Webflow.class);
		query.setParameter("ip", ip);
		query.setParameter("date", date);
		Webflow myBean = query.uniqueResult();
		if (myBean != null) {
			return myBean; // 銵函內'??靘?
		}
		return myBean; // meBean==null,銵函內'瘝?'靘?		
	}
	
	//有來過->增加次數
	public Webflow updateTimes(Webflow webflow) {
		Integer times = webflow.getLogtime();
		webflow.setLogtime((times+1));
		return webflow;		
	}
	//當天不重複
	public List<Webflow> IpnoRepeatDay(String date){
		Query<Webflow> query = sessionFactory.getCurrentSession()
		.createQuery("select DISTINCT ip from Webflow where DateDiff(day,'"+date+"',getdate())=0");
		List<Webflow> list = query.list();
		return list;
		
	}
	
	//7天不重複
	public List<Webflow> IpnoRepeatWeek(String date){
		
		Query<Webflow> query = sessionFactory.getCurrentSession()
		.createQuery("select DISTINCT ip from Webflow where DateDiff(day,'"+date+"',getdate())<=7");
		List<Webflow> list = query.list();
		return list;		
	}
	//1天總瀏覽
	public List<Webflow> IpRepeatDay(String date){
		Query<Webflow> query = sessionFactory.getCurrentSession()
		.createQuery("from Webflow where DateDiff(day,'"+date+"',getdate())=0", Webflow.class);
		List<Webflow> list = query.list();
		return list;
		
	}
	
	//7天總瀏覽
	public List<Webflow> IpRepeatWeek(String date){
		Query<Webflow> query = sessionFactory.getCurrentSession()
		.createQuery("from Webflow where DateDiff(day,'"+date+"',getdate())<=7", Webflow.class);
		List<Webflow> list = query.list();
		return list;		
	}
	
}
