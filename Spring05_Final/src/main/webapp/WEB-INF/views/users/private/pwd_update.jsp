<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/pwd_update.jsp</title>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${requestScope.isSuccess }">
			<p><strong>${id }</strong>님 비밀 번호를 수정했습니다.</p>
			<a href="${pageContext.request.contextPath}/users/private/info.do">확인</a>
		</c:when>
		<c:otherwise>
			<p>
				<strong>${id } 님</strong> 비밀번호가 일치 하지 않습니다. 
				<a href="${pageContext.request.contextPath}/users/private/pwd_updateform.jsp">다시 시도</a>
			</p>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>