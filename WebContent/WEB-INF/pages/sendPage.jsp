<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Send Page</title>
</head>
<body>
<%
    String userName = request.getParameter("userName");
    String email = request.getParameter("email");
    String sub = email.substring(email.indexOf('@') + 1,email.lastIndexOf('.'));
    String url="";
    if (sub.equals("gmail") || sub.equals("gm")) { 
    	url = "https://www.google.com/gmail/"; 
    }else if(sub.equals("yahoo.com")){
    	url = "https://tw.mail.yahoo.com/mail/"; 
    }else if(sub.equals("msa.hinet")){
    	url = "https://webmail.hinet.net/"; 
    }
    
%>
<h4>${userName}您的信箱<a target="_blank" href="<%=url %>">${email}</a></h4>還沒確認，<br/>您必須盡快進行帳戶確認（登錄信箱，並按照帳號確認通知的指示操作）。<br/>
否則您的帳號將很快被刪除！<br/>
您還沒有收到確認信？點擊<a href="${pageContext.request.contextPath}/gmailregister?account=${userName}&email=<%=email%>">這裡</a>重新發送確認信。

</body>
</html>