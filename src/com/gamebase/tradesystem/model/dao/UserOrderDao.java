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

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.InvoiceObj;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Repository
public class UserOrderDao {
	private SessionFactory sessionFactory;
	
	private InvoiceObj invoice = null;

	@Autowired
	public UserOrderDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String processOrder() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Session session = sessionFactory.getCurrentSession();
		Date date = new Date();

		AllInOne Ecpay = new AllInOne("");
		AioCheckOutALL order = new AioCheckOutALL();
		order.setChoosePayment("Credit");
		order.setMerchantID("2000132");
		order.setMerchantTradeNo(this.makeUUID());// 要用UUID加密產生，不可重複
		order.setMerchantTradeDate(String.valueOf(sdFormat.format(date)));
		order.setPaymentType("aio");
		order.setTotalAmount("1000");//前端引入
		order.setTradeDesc("遊戲片");
		order.setItemName("遊戲名子");//前端引入
		order.setReturnURL("123");
		order.setClientBackURL("https://a7612c5a.ngrok.io/EcpayTest/callback.jsp");
		String str = Ecpay.aioCheckOut(order, invoice);
		System.out.println(str);
		return str;
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
		StringBuffer MerchantTradeNo = new StringBuffer("gbitem");
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
