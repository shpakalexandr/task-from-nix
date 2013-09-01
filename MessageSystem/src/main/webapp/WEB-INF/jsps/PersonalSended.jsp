<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sended Messages</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="logout.jsp"></jsp:include>
	<table width="100%">
		<tr>
			<td align="left"><B><FONT SIZE=3 COLOR=BLACK>Отправленные
						сообщения участника <B><FONT SIZE=4 COLOR=BLUE><c:out
									value="${username}" /></FONT></B>
				</FONT></B></td>
		</tr>
	</table>

	<table border="3" width="100%">
		<tr bgcolor="silver">
			<td width="100">Получатель</td>
			<td width="100">Тема</td>
			<td>Сообщение</td>
			<td width="100">Actions</td>
		</tr>
		<c:forEach items="${mesList}" var="cur">
			<tr>
				<td><c:out value="${cur.whoreceivemessagenickname}" /></td>
				<td><c:out value="${cur.messagetitle}" /></td>
				<td><c:out value="${cur.messagebody}" /></td>
				<td><c:url var="deleteMessUrl"
						value="/DeleteMessage?id=${cur.messageid}" /><a
					href="${deleteMessUrl}">Удалить</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>