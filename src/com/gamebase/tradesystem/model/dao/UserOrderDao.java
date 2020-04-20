package com.gamebase.tradesystem.model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.UserOrder;
import com.google.gson.Gson;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.InvoiceObj;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Repository
public class UserOrderDao {
	private SessionFactory sessionFactory;
	
	private InvoiceObj invoice = null;

	private SimpleDateFormat sdFormat;

	private Date date;

	@Autowired
	public UserOrderDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String processOrder() {
		sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Session session = sessionFactory.getCurrentSession();
		date = new Date();

		AllInOne Ecpay = new AllInOne("");
		AioCheckOutALL order = new AioCheckOutALL();
		order.setChoosePayment("Credit");
		order.setMerchantID("2000132");
		String uuid = this.makeUUID();
		order.setMerchantTradeNo(uuid);// 要用UUID加密產生，不可重複
		System.out.println(uuid);
		String date1 = String.valueOf(sdFormat.format(date));
		System.out.println("data1:"+date1);
		order.setMerchantTradeDate(date1);
		order.setPaymentType("aio");
		order.setTotalAmount("1000");//前端引入
		order.setTradeDesc("Game");//不能中文
		order.setItemName("Game1");//不能中文前端引入
		order.setReturnURL("/shoppingCart/addOrder");
		order.setClientBackURL("http://localhost:8080/GameBase/shoppingPage");
		String str = Ecpay.aioCheckOut(order, invoice);
		System.out.println(str);
		return str;
	}
	public void addOrder(String form) {
		UserOrder uo = new Gson().fromJson(form, UserOrder.class);
		uo.setPayStatus(0);
		uo.setOrderDate(date);
		System.out.println(date);
	}

	public JSONArray query() {

		return null;
	}

	public JSONObject delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject update(String b) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray search(String a) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray getSearch(String a) {
		// TODO Auto-generated method stub
		return null;
	}

	public String makeUUID() {

		Date date = new Date();

//		System.out.println("---");

		UUID idd = UUID.randomUUID();
		String[] iddd = idd.toString().split("-");
		System.out.println(iddd[0]);
		SimpleDateFormat sFormat = new SimpleDateFormat("yyMMdd");
//		System.out.println(sFormat.format(date));
		StringBuffer MerchantTradeNo = new StringBuffer("GBitem");
//		System.out.println("MerchantTradeNo=" + MerchantTradeNo);
		MerchantTradeNo.insert(MerchantTradeNo.length(), String.valueOf(sFormat.format(date)));
//		System.out.println("MerchantTradeNo=" + MerchantTradeNo);
		MerchantTradeNo.insert(MerchantTradeNo.length(), iddd[0]);
//		System.out.println("MerchantTradeNo=" + MerchantTradeNo);
		String MerchantTradeNo2 = MerchantTradeNo.toString();
//		System.out.println("MerchantTradeNo2=" + MerchantTradeNo2);
		return MerchantTradeNo2;
	}

}
