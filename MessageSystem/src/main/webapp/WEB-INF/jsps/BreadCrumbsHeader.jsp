<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logout</title>
</head>
<body>
	<table width="100%" height="25">
		<tr>
			<c:if test="${breadcrumbs.parent1 != null}">
				<td><B><FONT SIZE=2><a
							href="${breadcrumbs.parent1.url}">${breadcrumbs.parent1.name}</a></FONT></B></td>
			</c:if>
			<c:if test="${breadcrumbs.parent2 != null}">
				<td><B><FONT SIZE=2> &gt;</FONT></B></td>
				<td><B><FONT SIZE=2><a
							href="${breadcrumbs.parent2.url}">${breadcrumbs.parent2.name}</a></FONT></B></td>
			</c:if>
			<c:if test="${breadcrumbs.current != null}">
				<td><B><FONT SIZE=2>&gt;</FONT></B></td>
				<td><B><FONT SIZE=2>${breadcrumbs.current.name}</FONT></B></td>
			</c:if>
			<td class="current"></td>
			<td align="right"><B><FONT SIZE=2><a
						href="<c:url value="j_spring_security_logout" />">Logout</a></FONT></B></td>
		</tr>
	</table>
</body>
</html>
