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
	<!-- novalidate 는 웹브라우저가 기본으로 갖고있는 자체 유효성 검증을 하지 못하도록 하는 설정(유효성 검증을 개발자가 직접 관리하겠다는 의미) -->
	<form name="myForm" action="insert.jsp" method="post" novalidate>
		<!-- 입력한 문자열을 id 라는 모델명으로 관리, 반드시 입력해야 한다. -->
		<!-- 
			한 글자 이상 입력했는지 여부를 체크하는 ng-required 속성.
			한 글자 이상 입력해있어야 ng-required가 true가 나온다.(한 글자도 입력하지 않으면 ng-required의 속성 값은 false가 나온다.) 
		-->
		<input type="text" name="id" ng-model="id" ng-required="true" />
		<!-- 해당 페이지가 처음 로딩됐을 때는 안나오다가 아이디에 한번이라도 입력을 했고 유효하지 않은 값(dirty)일 때 표출 -->
		<p ng-show="myForm.id.$invalid && myForm.id.$dirty" style="color:red;">아이디는 반드시 입력 해라!</p>
		<!-- 해당 페이지가 처음 로딩됐을 때는 안나오다가 아이디에 한번이라도 입력을 했고 유효한 값(pristine)일 때 숨김  -->
		<p ng-hide="myForm.id.$valid || myForm.id.$pristine" style="color:red;">아이디는 반드시 입력 해라2!</p>
		<button type="submit" ng-disabled="myForm.id.$invalid">제출</button>
	</form>
	<p> 입력한 아이디 : <strong>{{id}}</strong></p>
	<p> 아이디 유효한지 여부 : <strong>{{myForm.id.$valid}}</strong></p>
	<p> 아이디 유효하지 않은 지 여부 : <strong>{{myForm.id.$invalid}}</strong></p>
	<!-- 해당 페이지가 처음 로딩되고 아이디가 한번이라도 입력을 했는 지(dirty) 안했는지(pristine) 여부  -->
	<p> 아이디가 청결(순결) 한지 여부 : <strong>{{myForm.id.$pristine}}</strong></p>
	<p> 아이디가 더렵혀졌는 지 여부 : <strong>{{myForm.id.$dirty}}</strong></p>
</div>
</body>
</html>