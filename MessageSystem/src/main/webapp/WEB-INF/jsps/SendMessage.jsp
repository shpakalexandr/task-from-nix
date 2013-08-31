<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send Message</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="logout.jsp"></jsp:include>
	<form name="send" action="MessageSend" method="post">
		<table align="center">
			<tr>
				<td align="right">Получатель:&emsp;</td>
				<td><input type="text" name="messagereciever" size="20" value="${messagereciever}"><B><FONT SIZE=3 COLOR=RED>
					<c:out value="${recievererrorstar}" /></FONT></B><input name="submit"
					type="submit" value="Создать" /></td>
			</tr>

			<tr>
				<td align="right">Тема:&emsp;</td>
				<td><input type="text" name="messagetitle" size="84" value="${messagetitle}">
				<B><FONT SIZE=3 COLOR=RED><c:out value="${titleererrorstar}" /></FONT></B></td>
			</tr>

			<tr>
				<td align="right" valign="top">Сообщение:&emsp;</td>
				<td><textarea name="messagebody" cols="65" rows="30"></textarea></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${ErrMsgs != null}">
				<table>
					<tr>
						<td><B><FONT SIZE=3 COLOR=RED>Ошибки:</FONT></B></td>
					</tr>
					<c:forEach items="${ErrMsgs}" var="error">

						<tr>
							<td><B><FONT SIZE=2 COLOR=RED><c:out
											value="${error}" /></FONT></B></td>
						</tr>

					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</form>
</body>
</html>