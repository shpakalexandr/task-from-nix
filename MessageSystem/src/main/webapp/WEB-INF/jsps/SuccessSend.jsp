<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S U C C E S S</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="BreadCrumbsHeader.jsp"></jsp:include>
	<form name='f' action="login" method='POST'>
		<table>
			<tr height="20">
				<td></td>
			</tr>
			<tr>
			<tr>
				<td align="center"><B><FONT SIZE=10 COLOR=BLACK>Ваше
							сообщение успешно отправлено!</FONT></B></td>
			</tr>
			<tr height="20">
				<td></td>
			</tr>
			<tr>
				<td align="center"><input name="submit" type="submit"
					value="Back to Main Menu" /></td>
			</tr>
		</table>
	</form>
</body>
</html>