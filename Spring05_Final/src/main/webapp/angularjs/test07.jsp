<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- angularjs 로딩 시키기 -->
<script src="../resources/js/angular.min.js"></script>
<script>
	// "myApp" 이라는 이름의 모듈 만들기
	var myApp = angular.module("myApp", []);
	// 모듈을 이용해서 myCtrl 이라는 이름의 컨트롤러 만들기(특정 div에 지정된 이름(ng-controller="myCtrl")의 컨트롤러에 속해있는 공간을 myCtrl 컨트롤러로 제어하겠다는 의미)
	myApp.controller("myCtrl", function($scope){
		$scope.nums=[10,20,30,40,50];
		$scope.mem={num:1, name:"김구라"};
		$scope.msg="empty";
		$scope.obj={};
	});
</script>
</head>
<body ng-app="myApp">
	<div ng-controller="myCtrl">
		<ul>
			<!-- ng-bind="tmp" 와 {{tmp}}는 같은 뜻 -->
			<li ng-repeat="tmp in nums" ng-bind="tmp"></li>
		</ul>
		<p>번호 : {{mem.num}}</p>
		<p>이름 : <span ng-bind="mem.name"></span></p>
		<!-- input 요소안에 ng-model 속성의 값은 value="empty" 한 것과 같다.(페이지 로딩할 때 msg라는 모델(방)에 문자열 "empty"가 저장되기 때문) -->
		<input type="text" ng-model="msg" />
		<p> msg : <strong ng-bind="msg"></strong></p>
		<!-- obj.height와 obj.weight 같은 표현식은 obj라는 오브젝트안에 키를 height, weight로 설정하겠다는 의미. -->
		<p> obj : <strong>{{obj}}</strong></p>
		<input type="text" ng-model="obj.height" placeholder="키 입력..." />
		<input type="text" ng-model="obj.weight" placeholder="몸무게 입력..." />
	</div>
</body>
</html>