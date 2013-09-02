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
	<jsp:include page="BreadCrumbsHeader.jsp"></jsp:include>
	<form name='f' action="CreateUser" method='POST'>
		<table width="100%">
			<tr>
				<td align="left"><B><FONT SIZE=3 COLOR=BLACK>Список
							участников в системе</FONT></B></td>
				<sec:accesscontrollist hasPermission="" domainObject=""></sec:accesscontrollist>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td align="right"><input name="submit" type="submit"
						value="Создать нового участника" /></td>
				</sec:authorize>
			</tr>
		</table>
	</form>

	<table border="3" width="100%">
		<tr bgcolor="silver">
			<td width="150">Фамалия</td>
			<td width="150">Имя</td>
			<td width="150">Никнэйм</td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td width="150">Пароль</td>
				<td>Actions</td>
			</sec:authorize>
		</tr>
		<c:forEach items="${listUsers}" var="current">
			<tr>
				<td><c:out value="${current.userlastname}" /></td>
				<td><c:out value="${current.userfirstname}" /></td>
				<td><c:out value="${current.usernickname}" /></td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td><c:out value="${current.userpassword}" /></td>
					<td>
						<table width="100%">
							<c:url var="modifyUrl" value="/ModifyUser?id=${current.userid}" />
							<c:url var="deleteUrl" value="/DeleteUser?id=${current.userid}" />
							<c:url var="receivedUrl"
								value="/AdministrationReceived?id=${current.userid}" />
							<c:url var="sendedUrl"
								value="/AdministrationSended?id=${current.userid}" />
							<tr>
								<td><a href="${modifyUrl}">Модифицировать</a></td>
								<td><a href="${deleteUrl}">Удалить</a></td>
								<td><a href="${receivedUrl}">Полученные</a></td>
								<td><a href="${sendedUrl}">Отправленные</a></td>
							</tr>
						</table>
					</td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</body>
</html>