package com.gamebase.member.model.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.Rank;
import com.gamebase.tradesystem.model.OrderDetail;
import com.gamebase.tradesystem.model.UserOrder;
import com.google.gson.Gson;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.InvoiceObj;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class RankDAO implements IRankDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Date date;
	private InvoiceObj invoice;

	@Override
	public Rank getByRankId(Integer rankId) {
		Rank rank = sessionFactory.getCurrentSession().get(Rank.class, rankId);
		System.out.println("Rank: " + rank);
		return rank;
	}

	@Override
	public List<Rank> getAllRank() {
		List<Rank> ranks = sessionFactory.getCurrentSession().createQuery("From Rank", Rank.class).list();
		return ranks;
	}
	
	public String processRankOrder(Integer userid) {
		Session session = sessionFactory.getCurrentSession();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		Timestamp ts = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		try {
		String uuid = this.makeRankUUID();
		

		AllInOne Ecpay = new AllInOne("");
		AioCheckOutALL order = new AioCheckOutALL();
		order.setChoosePayment("Credit");
		order.setMerchantID("2000132");
		order.setMerchantTradeNo(uuid);// 要用UUID加密產生，不可重複
//		order.setCustomField1(uo.getOrderName());
//		order.setCustomField2(uo.getOrderPhone());
//		order.setCustomField3(uo.getOrderAddress());
		order.setCustomField1(String.valueOf(userid));
		order.setMerchantTradeDate(String.valueOf(sdFormat.format(date)));
		order.setPaymentType("aio");
		order.setTotalAmount("500");//前端引入
		order.setTradeDesc("Game");//不能中文
		order.setItemName("GameBase Member Fee");//不能中文前端引入
		order.setReturnURL(" https://74c392d3.ngrok.io/GameBase/rankOrderStatus");
		order.setClientBackURL(" https://74c392d3.ngrok.io/GameBase");
		String str = Ecpay.aioCheckOut(order, invoice);
		System.out.println(str);
		return str;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void rankOrderStatus(int rtnCode,Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("userId:"+userId);
		if(rtnCode==1) {
		
			System.out.println("訂單建立成功");
		}else {
			System.out.println("訂單建立失敗");
		}
	}
	
	public String makeRankUUID() {

		Date date = new Date();


		UUID idd = UUID.randomUUID();
		String[] iddd = idd.toString().split("-");
		System.out.println(iddd[0]);
		SimpleDateFormat sFormat = new SimpleDateFormat("yyMMdd");
		StringBuffer MerchantTradeNo = new StringBuffer("GBitem");
		MerchantTradeNo.insert(MerchantTradeNo.length(), String.valueOf(sFormat.format(date)));
		MerchantTradeNo.insert(MerchantTradeNo.length(), iddd[0]);
		String MerchantTradeNo2 = MerchantTradeNo.toString();
		return MerchantTradeNo2;
	}
}
