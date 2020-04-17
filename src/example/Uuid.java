package example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Uuid {
	public Uuid() {

	}

	public static String getUuid() {
//		System.out.println("uid=" + uid);
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
