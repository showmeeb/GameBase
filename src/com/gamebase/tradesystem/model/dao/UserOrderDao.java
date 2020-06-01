package com.gamebase.tradesystem.model.dao;

import java.sql.Timestamp;
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


	private Date date;

	@Autowired
	public UserOrderDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String processOrder(String form,String items1) {
		Session session = sessionFactory.getCurrentSession();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		Timestamp ts = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		try {
		String uuid = this.makeUUID();
		JSONArray items = JSONArray.fromObject(items1);
		UserOrder uo = new Gson().fromJson(form, UserOrder.class);
		System.out.println(uo.getUserId()+"ididididididi");
		uo.setUuId(uuid);
		uo.setOrderDate(ts);
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
		order.setMerchantTradeDate(String.valueOf(sdFormat.format(date)));
		order.setPaymentType("aio");
		order.setTotalAmount(String.valueOf(uo.getOrderPrice()));//前端引入
		order.setTradeDesc("GameBase");//不能中文
		order.setItemName("Please check your payment's detail in  Emailbox");//不能中文前端引入
		order.setReturnURL("http://7306c43d.ngrok.io/GameBase/shoppingCart/orderStatus");
		order.setClientBackURL("http://7306c43d.ngrok.io/GameBase/epay");
		String str = Ecpay.aioCheckOut(order, invoice);
		System.out.println(str);
		return str;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public JSONObject orderStatus(int rtnCode,int orderId) {
		Session session = sessionFactory.getCurrentSession();
		JSONObject jobj = new JSONObject();
		UserOrder uo = (UserOrder)session.get(UserOrder.class,orderId);
		jobj.put("userId", uo.getUserId());
		try {
			System.out.println("orderId:"+orderId);
			if(rtnCode==1) {
				System.out.println("999");
				
				System.out.println("99");
				uo.setPayStatus(1);
				System.out.println("9");
				session.update(uo);
				System.out.println("訂單建立成功");
				jobj.put("t",this.checkRank(uo.getUserId()));
			}else {
				System.out.println("訂單建立失敗");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(jobj);
		return jobj;
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
				jobj.put("orderDate", String.valueOf(beans.getOrderDate()));
				if(beans.getPayStatus()==1) {
					jobj.put("payStatus", "已繳費");
				}else {
					jobj.put("payStatus", "未繳費");
				}
				jobj.put("orderEmail", beans.getOrderEmail());
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

	public boolean checkRank(int userId) {
		Session session = sessionFactory.getCurrentSession();
		int total=0;
		try {
			Query<UserOrder> query = session.createQuery("from UserOrder where userId=?1",UserOrder.class);
			query.setParameter(1,userId);
			List<UserOrder> list = query.getResultList();
				for(UserOrder beans:list) {
					
					total+=beans.getOrderPrice();
				}
				if(total>10000) {
					return true;
				}
				return false;
			}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
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
	
	public List<UserOrder> allOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserOrder> query = session.createQuery("from UserOrder",UserOrder.class);
		List<UserOrder> list = query.list();
		return list;
	}
	public List<UserOrder> notMemberOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserOrder> query = session.createQuery("from UserOrder where userId=1",UserOrder.class);
		List<UserOrder> list = query.list();
		return list;
	}
	public List<UserOrder> MemberOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserOrder> query = session.createQuery("from UserOrder where userId!=1",UserOrder.class);
		List<UserOrder> list = query.list();
		return list;
	}
}
