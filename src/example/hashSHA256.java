package example;
/*
 * 加密用方法
 * */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class hashSHA256 {

	public hashSHA256() {
		// TODO Auto-generated constructor stub
	}
	public static String getSHA256StrJava(String str){
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
		messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(str.getBytes("UTF-8"));
		encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		}
		return encodeStr;
		}
	private static String byte2Hex(byte[] bytes){
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i=0;i<bytes.length;i++){
		temp = Integer.toHexString(bytes[i] & 0xFF);
		if (temp.length()==1){
		//1得到一位的進行補0操作
		stringBuffer.append("0");
		}
		stringBuffer.append(temp);
		}
		return stringBuffer.toString();
		}


}
