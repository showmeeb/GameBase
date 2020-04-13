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
    String sub = email.substring(email.indexOf('@') + 1);
    if (sub.equals("gmail")) { sub = "google"; } 
    String url = "https://www.google.com/gmail/";
%>
${userName}您的信箱<a target="_blank" href="<%=url %>">${email}</a>還沒確認，您必須盡快進行帳戶確認（登錄信箱，並按照帳號確認通知的指示操作）。否則您的帳號將很快被刪除！<br/>
您還沒有收到確認信？點擊<a href="${pageContext.request.contextPath}/gmailregister?account=<%=userName%>&email=<%=email%>">這裡</a>重新發送確認信。

</body>
</html>