package com.gamebase.member.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gamebase.member.model.MailInfo;

@Repository
public class MailSenderDAO {

	public Map<String,String> mailAction(String acc,String email){
		Map<String,String> map = new HashMap<String,String>();
		
		int i = (int) (Math.random() * (99999999 - 1000 + 1) + 1000);
		String registerId = "" + i;
		map.put("registerId",registerId);
		String url = "http://localhost:8080/GameBase/mailback/" + registerId;
		map.put("url",url);
		String content = acc + "(" + email + "),您好<br/>感谢您註冊GameBase!<br/>" + "<b>驗證您的註冊信箱</b><br/>請點擊鏈結來確認您的註冊<br/>"
				+ "<a href='" + url + "'>確認!請點擊這裡來驗證您的信箱</a><br/>"
				+ "如果您不能點擊上述標籤為“確認！”的鏈接，您還可以通過複製（或輸入）下面的URL到地址欄中來驗證您的郵件地址。<br/>" + "<a href='" + url + "'>" + url
				+ "</a><br/>" + "如果您認為這是垃圾郵件，請忽略此郵件。";
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
		
		return map;
	}
	
	
	
}
