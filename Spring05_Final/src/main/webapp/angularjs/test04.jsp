<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test04.jsp</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<!-- angularjs 로딩 시키기 -->
<script src="../resources/js/angular.min.js"></script>
</head>
<!-- ng-app 속성을 통해 body 에서 일어나는 일은 angular 로 관리를 하겠다! 라는 의미 -->
<body ng-app>
<div class="container">
	<h1>form 검증</h1>
	<form name="myForm" action="insert.jsp" method="post" novalidate>
		<!-- 입력한 문자열을 id 라는 모델명으로 관리, 반드시 입력해야 한다. -->
		<input type="text" name="id" ng-model="id" ng-required="true" />
		
		<button type="submit" ng-disabled="myForm.id.$invalid">제출</button>
	</form>
	<p> 입력한 아이디 : <strong>{{id}}</strong></p>
	<p> 아이디 유효한지 여부 : <strong>{{myForm.id.$valid}}</strong></p>
	<p> 아이디 유효하지 않은 지 여부 : <strong>{{myForm.id.$invalid}}</strong></p>
	
</div>
</body>
</html>