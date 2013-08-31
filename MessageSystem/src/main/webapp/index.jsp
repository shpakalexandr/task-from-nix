<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Message System</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsps/header.jsp"></jsp:include>
	<form name='f' action="login" method='POST'>
		<table>
			<tr height="20">
				<td></td>
			</tr>
			<tr>
			<tr>
				<td>Добро пожаловать в систему обмена сообщениями! Чтобы
					продолжить работу, вам необходимо войти в систему. Для этого,
					нажмите кнопку "Login" и введите свои логин и пароль.</td>
			</tr>
			<tr height="20">
				<td></td>
			</tr>
			<tr>
				<td align="center"><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>