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
		<div class="form-group">
			<label for="id">아이디</label>
			<!-- ng-class는 class의 추가 및 삭제를 boolean 타입의 결과값(true or false)으로 관리한다. -->
			<input ng-model="id" ng-required="true" type="text" name="id" id="id" class="form-control"
				ng-class="{'is-invalid': myForm.id.$invalid && myForm.id.$dirty, 'is-valid': myForm.id.$valid}" />
			<div class="invalid-feedback">아이디는 반드시 입력하세요.</div>
			<div class="valid-feedback">아이디를 제대로 입력 했습니다.</div>
		</div>
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