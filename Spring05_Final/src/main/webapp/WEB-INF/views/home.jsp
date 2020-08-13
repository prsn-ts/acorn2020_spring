<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부 파일 로드할 때의 과정 보기
	밑에 bootstrap.css 를 적용받기위해서 home.do를 요청했을 때(서버에서 비즈니스 로직 처리 후 home.jsp가 응답될 때, 보통 인덱스 페이지가 응답될 때) 같이 요청해서
	서버로부터 응답받는 것이 아니고 home.do 요청에의해 응답되는 home.jsp가 로드되고 난 후에 서버로부터 home.jsp에 응답받은 문자열(jsp 페이지)를 해석하고
	난 이후에 link요소등에 써있는 문자열을 보고 css를 추가 로딩해야할 때(css 외부파일 로딩 등등) 다시 서버로부터 css 경로에 맞춰서 요청하게되어 css가 응답되고 적용이 된다.
 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<a href="${pageContext.request.contextPath}/users/loginform.do">로그인</a>
			<a href="${pageContext.request.contextPath}/users/signup_form.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<strong><a href="users/private/info.do">${sessionScope.id }</a></strong>님 로그인중...
			<a href="${pageContext.request.contextPath}/users/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
	<h1>인덱스 페이지 입니다.</h1>
	<p>Spring Framework 동작중 ...</p>
	<ul>
		<li><a href="file/list.do">자료실 목록 보기</a></li>
	</ul>
</div>
</body>
</html>