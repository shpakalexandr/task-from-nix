<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="BreadCrumbsHeader.jsp"></jsp:include>
	<c:choose>
		<c:when test="${CREATE}">
			<form name="cr" action="UserCreate" method="post">
				<table>
					<tr>
						<td align="left" width="500"><B><FONT SIZE=4>Заполните
									необходимые значения и нажмите кнопку</FONT></B></td>
						<td><input name="submit" type="submit" value="Создать" /></td>
					</tr>
				</table>

				<table>
					<tr>
						<td width="80">Фамилия:</td>
						<td><input type='text' name='cruserlastname'
							value='<c:out value="${cruserlastname}" />' /></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${crlastnameerrorstar}" /></FONT></B></td>
					</tr>
					<tr>
						<td width="80">Имя:</td>
						<td><input type='text' name='cruserfirstname'
							value='<c:out value="${cruserfirstname}" />' /></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${crfirstnameerrorstar}" /></FONT></B></td>
					</tr>
					<tr>
						<td width="80">Никнейм:</td>
						<td><input type='text' name='crusernickname'
							value='<c:out value="${crusernickname}" />'></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${crnicknameerrorstar}" /></FONT></B></td>
					</tr>
					<tr>
						<td width="80">Пароль:</td>
						<td><input type='text' name='cruserpassword'
							value='<c:out value="${cruserpassword}" />' /></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${crpassworderrorstar}" /></FONT></B></td>
					</tr>
				</table>
			</form>
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
		</c:when>
		<c:otherwise>
			<form name="mod" action="UserModify" method="post">
				<table>
					<tr>
						<td align="left" width="500"><B><FONT SIZE=4>Измените
									необходимые значения и нажмите кнопку</FONT></B></td>
						<td><input name="submit" type="submit" value="Применить" /></td>
					</tr>
				</table>

				<table>
					<tr>
						<td width="80">Фамилия:</td>
						<td><input type='text' name='moduserlastname'
							value='${userInfo.userlastname}' /></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${modlastnameerrorstar}" /></FONT></B></td>
					</tr>
					<tr>
						<td width="80">Имя:</td>
						<td><input type='text' name='moduserfirstname'
							value='${userInfo.userfirstname}' /></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${modfirstnameerrorstar}" /></FONT></B></td>
					</tr>
					<tr>
						<td width="80">Никнейм:</td>
						<td><input type='text' name='modusernickname'
							value='${userInfo.usernickname}'></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${modnicknameerrorstar}" /></FONT></B></td>
					</tr>
					<tr>
						<td width="80">Пароль:</td>
						<td><input type='text' name='moduserpassword'
							value='${userInfo.userpassword}' /></td>
						<td><B><FONT SIZE=2 COLOR=RED><c:out
										value="${modpassworderrorstar}" /></FONT></B></td>
					</tr>
				</table>
			</form>
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
		</c:otherwise>
	</c:choose>

</body>
</html>