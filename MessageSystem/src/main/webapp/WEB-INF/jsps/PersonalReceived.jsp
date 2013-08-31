<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="logout.jsp"></jsp:include>
		<table width="100%">
			<tr>
				<td align="left"><B><FONT SIZE=3 COLOR=BLACK>Список
						полученных сообщений</FONT></B></td>
			</tr>
		</table>

	<table border="3" width="100%">
		<tr bgcolor="silver">
			<td>Отправитель</td>
			<td>Тема</td>
			<td>Сообщение</td>
			<td>Actions</td>
		</tr>
		<c:forEach items="${messList}" var="current">
			<tr>
				<td><c:out value="${current.whosendmessage}" /></td>
				<td><c:out value="${current.messagetitle}" /></td>
				<td><c:out value="${current.messagebody}" /></td>
				<td><c:url var="modifyUrl" value="/DeleteMessage?id=${current.messageowner}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>