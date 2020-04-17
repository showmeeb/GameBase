package example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.InvoiceObj;

@WebServlet("/EcPayServlet")
public class EcPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html;charset=utf-8");
		InvoiceObj invoice = null;
		String ckVlu="";
		String MerchantTradeNo = Uuid.getUuid();
		System.out.println("---");
		
		Enumeration<String> names = request.getParameterNames();
		ckVlu+="HashKey="+request.getParameter("HashKey")+"&";
		ckVlu+="ChoosePayment="+request.getParameter("ChoosePayment")+"&";
		ckVlu+="EncryType=1&";
		ckVlu+="ItemName="+request.getParameter("ItemName")+"&";
		ckVlu+="MerchantID="+request.getParameter("MerchantID")+"&";
		ckVlu+="MerchantTradeDate="+request.getParameter("MerchantTradeDate")+"&";
		ckVlu+="MerchantTradeNo="+MerchantTradeNo+"&";
		ckVlu+="PaymentType="+request.getParameter("PaymentType")+"&";
		ckVlu+="ReturnURL="+request.getParameter("ReturnURL")+"&";
		ckVlu+="TotalAmount="+request.getParameter("TotalAmount")+"&";
		ckVlu+="TradeDesc="+request.getParameter("TradeDesc")+"&";
		ckVlu+="HashIV="+request.getParameter("HashIV")+"&";
		
		System.out.println(ckVlu);
		String encode = URLEncoder.encode(ckVlu, "utf-8");
		encode.toLowerCase();
		System.out.println("---");
		System.out.println(encode);		
		System.out.println("---");
		String check = hashSHA256.getSHA256StrJava(encode);
		System.out.println(check);		
		System.out.println("---");
		String check2 = check.toUpperCase();
		System.out.println(check2);		
		System.out.println("---");		
		
		AllInOne Ecpay =new AllInOne("");
		AioCheckOutALL order =new AioCheckOutALL();		
		order.setChoosePayment(request.getParameter("ChoosePayment"));
		order.setMerchantID(request.getParameter("MerchantID"));
		order.setMerchantTradeNo(MerchantTradeNo);//要用UUID加密產生，不可重複
		order.setMerchantTradeDate(request.getParameter("MerchantTradeDate"));
		order.setPaymentType(request.getParameter("PaymentType"));
		order.setTotalAmount(request.getParameter("TotalAmount"));
		order.setTradeDesc(request.getParameter("TradeDesc"));
		order.setItemName(request.getParameter("ItemName"));
		order.setReturnURL(request.getParameter("ReturnURL"));	
		order.setClientBackURL("https://a7612c5a.ngrok.io/EcpayTest/callback.jsp");
		System.out.println(MerchantTradeNo);
		String str = Ecpay.aioCheckOut(order, invoice);
		System.out.println(str);
		PrintWriter out = response.getWriter();
		out.write("<body>"+str+"</body>");
	}
}
