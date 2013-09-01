<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main menu</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="logout.jsp"></jsp:include>
	<p><a href="UsersList">Список участников в системе</a></p>
	<p><a href="SendMessage">Отправить сообщение</a></p>
	<p><a href="PersonalReceived">Полученные сообщения</a></p>
	<p><a href="PersonalSended">Отправленные сообщения</a></p>
</body>
</html>