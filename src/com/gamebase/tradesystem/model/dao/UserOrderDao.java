package com.gamebase.tradesystem.model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.tradesystem.model.OrderDetail;
import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.ShoppingCart;
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

	public String processOrder(String form,String items1) {
		sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Session session = sessionFactory.getCurrentSession();
		date = new Date();
		try {
		String uuid = this.makeUUID();
		String date1 = String.valueOf(sdFormat.format(date));
		JSONArray items = JSONArray.fromObject(items1);
		UserOrder uo = new Gson().fromJson(form, UserOrder.class);
		uo.setUuId(uuid);
		uo.setOrderDate(date1);
		uo.setPayStatus(0);
		session.save(uo);
		for(int i=0;i<items.size();i++) {
			OrderDetail od=(OrderDetail)JSONObject.toBean(items.getJSONObject(i), OrderDetail.class);
			od.setOrderId(uo.getOrderId());
			System.out.println("OrderId:"+uo.getOrderId());
			session.save(od);
		}
		AllInOne Ecpay = new AllInOne("");
		AioCheckOutALL order = new AioCheckOutALL();
		order.setChoosePayment("Credit");
		order.setMerchantID("2000132");
		order.setMerchantTradeNo(uuid);// 要用UUID加密產生，不可重複
//		order.setCustomField1(uo.getOrderName());
//		order.setCustomField2(uo.getOrderPhone());
//		order.setCustomField3(uo.getOrderAddress());
		order.setCustomField4(String.valueOf(uo.getOrderId()));
		order.setMerchantTradeDate(date1);
		order.setPaymentType("aio");
		order.setTotalAmount(String.valueOf(uo.getOrderPrice()));//前端引入
		order.setTradeDesc("Game");//不能中文
		order.setItemName("Game1");//不能中文前端引入
		order.setReturnURL("http://d7c0cc70.ngrok.io/GameBase/shoppingCart/orderStatus");
		order.setClientBackURL("http://d7c0cc70.ngrok.io/GameBase/shoppingPage");
		String str = Ecpay.aioCheckOut(order, invoice);
		System.out.println(str);
		return str;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void orderStatus(int rtnCode,int orderId) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("orderId:"+orderId);
		if(rtnCode==1) {
			System.out.println("999");
			UserOrder uo = (UserOrder)session.get(UserOrder.class,orderId);
			System.out.println("99");
			uo.setPayStatus(1);
			System.out.println("9");
			session.update(uo);
			System.out.println("訂單建立成功");
		}else {
			System.out.println("訂單建立失敗");
		}
	}

	public JSONArray orderQuery(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query<UserOrder> query = session.createQuery("from UserOrder where userId=?1",UserOrder.class);
			query.setParameter(1,id);
			List<UserOrder> list = query.getResultList();
			
			System.out.println("size:"+list.size());

			JSONArray jsonArray = new JSONArray();
			
			for(UserOrder beans:list) {
				int i =1;
				JSONObject jobj = new JSONObject();

				jobj.put("userId", beans.getUserId());
				jobj.put("uuId", beans.getUuId());
				jobj.put("orderId", beans.getOrderId());
				jobj.put("orderName", beans.getOrderName());
				jobj.put("orderPhone", beans.getOrderPhone());
				jobj.put("orderAddress", beans.getOrderAddress());
				jobj.put("orderPrice", beans.getOrderPrice());
				jobj.put("orderDate", beans.getOrderDate());
				jobj.put("payStatus", beans.getPayStatus());
				System.out.println("orderId:"+beans.getOrderId());
				Query<OrderDetail> query1 = session.createQuery("from OrderDetail where orderId=?1",OrderDetail.class);
				query1.setParameter(1,(int)beans.getOrderId());
				List<OrderDetail> list1 = query1.getResultList();
				System.out.println("size1:"+list1.size());
				
				for(OrderDetail beans1:list1) {
					if(i>list1.size()){
						i=1;
					};
					JSONArray jsonArray1 = new JSONArray();
					JSONObject jobj1 = new JSONObject();
					jobj1.put("productId", beans1.getProductId());
					jobj1.put("productName", beans1.getProductName());
					jobj1.put("productPrice", beans1.getProductPrice());
					jobj1.put("amount", beans1.getAmount());
					jsonArray1.add(jobj1);
					jobj.put(i,jsonArray1);
					i++;
				}
				jsonArray.add(jobj);
				
			}
			System.out.println(jsonArray);
			return jsonArray;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	public JSONObject delete(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		JSONObject result= new JSONObject();
		try {
		UserOrder uo = (UserOrder)session.get("from UserOrder", orderId);
		session.delete(uo);
		result.put("t", true);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("t", false);
		}finally{
			return result;
		}
	}

//	public JSONObject update(String form) {
//		Session session = sessionFactory.getCurrentSession();
//		JSONObject result= new JSONObject();
//		try {
//		UserOrder uo = (UserOrder)session.get("from UserOrder", orderId);
//		session.delete(uo);
//		result.put("t", true);
//		}catch(Exception e) {
//			e.printStackTrace();
//			result.put("t", false);
//		}finally{
//			return result;
//		}
//	}

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
