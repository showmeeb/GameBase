package com.gamebase.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.MailInfo;
import com.gamebase.tradesystem.model.OrderDetail;
import com.gamebase.tradesystem.model.UserOrder;

@Repository
public class MailSenderDAO {
	
	private SessionFactory sessionFactory;

	@Autowired
	public MailSenderDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Map<String,String> mailAction(String acc,String email){
		Map<String,String> map = new HashMap<String,String>();
		System.out.println("Mail send start.");
		
		int i = (int) (Math.random() * (99999 - 1000 + 1) + 1000);
		String registerId = "" + i;
		map.put("registerId",registerId);
		String url = "http://localhost:8080/GameBase/mailback/" + registerId;
		map.put("url",url);
		String content = acc + "(" + email + "),您好<br/>感谢您註冊GameBase!<br/>" + "<b>驗證您的註冊信箱</b><br/>請點擊鏈結來確認您的註冊<br/>"
				+ "<a href='" + url + "'>確認!請點擊這裡來驗證您的信箱</a><br/>"
				+ "如果您不能點擊上述標籤為“確認！”的鏈接，您還可以通過複製（或輸入）下面的URL到地址欄中來驗證您的郵件地址。<br/>" + "<a href='" + url + "'>" + url
				+ "</a><br/>" + "如果您認為這是垃圾郵件，請忽略此郵件。";
		
		String sendpage = "<body>\r\n" +  
				"<h4>"+acc+"您的信箱<a target=\"_blank\" href=\"https://www.google.com/gmail/\">"+email+"</a></h4>還沒確認，<br/>您必須盡快進行帳戶確認（登錄信箱，並按照帳號確認通知的指示操作）。<br/>\r\n" + 
				"否則您的帳號將很快被刪除！<br/>\r\n" + 
				"您還沒有收到確認信？點擊<a href=\"http://localhost:8080/GameBase/gmailregisterA?account="+acc+"&email="+email+"\">這裡</a>重新發送確認信。\r\n" + 
				"\r\n" + 
				"</body>";
		map.put("sendPage",sendpage);
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailSmtpHost("smtp.gmail.com");
		mailInfo.setFromAddress("z0983177929@gmail.com");
		mailInfo.setToAddress(email);
		mailInfo.setMailSmtpPort("587");
		mailInfo.setUserName("z0983177929@gmail.com");
		mailInfo.setPassword("K98david");
		mailInfo.setValidate(true);
		mailInfo.setSubject("GameBase verification");
		mailInfo.setContent(content);
		MailSender.sendMail(mailInfo, true);
		System.out.println("Mail send down.");
		
		return map;
	}
	
	public Map<String,String> authAction(String acc,String email){
		Map<String,String> map = new HashMap<String,String>();
		System.out.println("Mail send start.");
		
		int i = (int) (Math.random() * (99999 - 1000 + 1) + 1000);
		String registerId = "" + i;
		map.put("registerId",registerId);
		String url = "http://localhost:8080/GameBase/Users/" + registerId;
		map.put("url",url);
		String content = "新會員"+acc + "(" + email + "),您好<br/>感谢您註冊GameBase!<br/>" + "<b>驗證您的註冊信箱</b><br/>請輸入下面的驗證碼來確認您的註冊<br/>"
				+"<h3><a href='" + url + "'>"+registerId+"</a></h3>"+"<br/>如果您認為這是垃圾郵件，請忽略此郵件。";
		
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailSmtpHost("smtp.gmail.com");
		mailInfo.setFromAddress("z0983177929@gmail.com");
		mailInfo.setToAddress(email);
		mailInfo.setMailSmtpPort("587");
		mailInfo.setUserName("z0983177929@gmail.com");
		mailInfo.setPassword("K98david");
		mailInfo.setValidate(true);
		mailInfo.setSubject("GameBase verification");
		mailInfo.setContent(content);
		MailSender.sendMail(mailInfo, true);
		System.out.println("Mail send down.");
		
		return map;
	}
	
	public Map<String,String> password(String acc,String email){
		Map<String,String> map = new HashMap<String,String>();
		System.out.println("Mail send start.");
		
		int i = (int) (Math.random() * (99999 - 1000 + 1) + 1000);
		String registerId = "" + i;
		map.put("registerId",registerId);
		String url = "http://localhost:8080/GameBase/mailback/" + registerId;
		map.put("url",url);
		String content = "密碼重置";
		
		String sendpage = "<body>\r\n" +  
				"<h4>"+acc+"您的信箱<a target=\"_blank\" href=\"https://www.google.com/gmail/\">"+email+"</a></h4>還沒確認，<br/>您必須盡快進行帳戶確認（登錄信箱，並按照帳號確認通知的指示操作）。<br/>\r\n" + 
				"否則您的帳號將很快被刪除！<br/>\r\n" + 
				"您還沒有收到確認信？點擊<a href=\"http://localhost:8080/GameBase/gmailregisterA?account="+acc+"&email="+email+"\">這裡</a>重新發送確認信。\r\n" + 
				"\r\n" + 
				"</body>";
		map.put("sendPage",sendpage);
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailSmtpHost("smtp.gmail.com");
		mailInfo.setFromAddress("z0983177929@gmail.com");
		mailInfo.setToAddress(email);
		mailInfo.setMailSmtpPort("587");
		mailInfo.setUserName("z0983177929@gmail.com");
		mailInfo.setPassword("K98david");
		mailInfo.setValidate(true);
		mailInfo.setSubject("GameBase verification");
		mailInfo.setContent(content);
		MailSender.sendMail(mailInfo, true);
		System.out.println("Mail send down.");
		
		return map;
	}
	
	public Map<String,String> sendOrderDetail(int orderId){
		Session session = sessionFactory.getCurrentSession();
		UserOrder uo = session.get(UserOrder.class, orderId);
		Query<OrderDetail> query = session.createQuery("from OrderDetail where orderId=?1",OrderDetail.class);
		query.setParameter(1, orderId);
		List<OrderDetail> list = query.getResultList();
		Map<String,String> map = new HashMap<String,String>();
		System.out.println("Mail send start.");
		
		int i = (int) (Math.random() * (99999 - 1000 + 1) + 1000);
		String registerId = "" + i;
		map.put("registerId",registerId);
		String url = "http://localhost:8080/GameBase/Users/" + registerId;
		map.put("url",url);
		String order = "親愛的"+uo.getOrderName() + "(" + uo.getOrderEmail() + "),您好<br/>感谢您在GameBase購物!<br/>" +"<b>訂單資料:</b>";
			   order+="<hr>";
			   order+="<table ><thead ><tr>";
			   order+="<th scope='col'>No.</th><th scope='col'>orderId</th><th scope='col'>uuId</th><th scope='col'>orderName</th>";
			   order+="<th scope='col'>orderPhone</th><th scope='col'>orderAddress</th><th scope='col'>orderPrice</th><th scope='col'>orderDate</th>";
			   order+="<th scope='col'>payStatus</th></tr></thead>";
			   
			   order+="<tbody >";
			   order+="<th scope='row'>1</th>";
			   order+="<td>"+uo.getOrderId()+"</td>";
			   order+="<td>"+uo.getUuId()+"</td>";
			   order+="<td>"+uo.getOrderName()+"</td>";
			   order+="<td>"+uo.getOrderPhone()+"</td>";
			   order+="<td>"+uo.getOrderAddress()+"</td>";
			   order+="<td>"+uo.getOrderPrice()+"</td>";
			   order+="<td>"+uo.getOrderDate()+"</td>";
			   order+="<td>"+uo.getPayStatus()+"</td>";
			   order+="</tbody></table>";
		    
			   order+="<hr>";
			   order+="<table><thead>";
			   order+="<tr><th scope='col'>#</th><th scope='col'>商品ID</th><th scope='col'>商品名稱</th><th scope='col'>商品價錢</th><th scope='col'>商品數量</th><th scope='col'>總價</th></tr></thead>";
			   order+="<tbody>";
			   
			   
			   
			   for(OrderDetail beans:list) {
				   int x =1;
				   order+="<tr>"+"<th scope='row'>"+(x+1)+"</th>";
				   order+="<td>"+beans.getProductId()+"</td>";
				   order+="<td>"+beans.getProductName()+"</td>";
				   order+="<td>"+beans.getProductPrice()+"</td>";
				   order+="<td>"+beans.getAmount()+"</td>";
				   order+="<td>"+(beans.getProductPrice()*beans.getAmount())+"</td>";
				   x++;
			   }
			  
			  order+="</tbody></table>";
		
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailSmtpHost("smtp.gmail.com");
		mailInfo.setFromAddress("z0983177929@gmail.com");
		mailInfo.setToAddress(uo.getOrderEmail());
		mailInfo.setMailSmtpPort("587");
		mailInfo.setUserName("z0983177929@gmail.com");
		mailInfo.setPassword("K98david");
		mailInfo.setValidate(true);
		mailInfo.setSubject("GameBase verification");
		mailInfo.setContent(order);
		MailSender.sendMail(mailInfo, true);
		System.out.println("Mail send down.");
		
		return map;
	}
	
}
