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
	// 추가적을 자바스크립트를 로딩해서 사용하려고할 때 angular.module()의 대괄호 []안에다가 명시한다.
	var myApp = angular.module("myApp", []);
	// 모듈을 이용해서 myCtrl 이라는 이름의 컨트롤러 만들기(특정 div에 지정된 이름(ng-controller="myCtrl")의 컨트롤러에 속해있는 공간을 myCtrl 컨트롤러로 제어하겠다는 의미)
	myApp.controller("myCtrl", function($scope){
		/*
		- 이 함수는 페이지가 로딩되는 시점에 최초 한번 호출된다.
		- $scope 에는 angular 가 관리하는 특별한 객체가 전달된다.
		- $scope 는 해당 컨트롤러에서 사용하는 영역 객체이다.
		- $scope 에는 각각의 컨트롤러에서 사용하는 모델(데이터)가 저장된다.
		- ng-model="" 이렇게 생성한 저장공간은 사실은 scope영역에서 만들어 낸다.
		*/
		console.log("myCtrl 에 있는 함수 호출됨");
		console.log($scope); //scope 객체보기
		//페이지 로딩 시점에 $scope object 에 nick 이라는 방(모델)을 만들고 문자열 저장하기
		$scope.nick="김구라";
		//페이지 로딩 시점에 $scope object 에 함수 정의(저장)하기(나중에 호출될 때 사용하기 위함)
		$scope.btnClicked=function(){
			alert("버튼을 눌렀네요?!");
		}
	});
	// 모듈을 이용해서 yourCtrl 이라는 이름의 컨트롤러 만들기(특정 div에 지정된 이름(ng-controller="yourCtrl")의 컨트롤러에 속해있는 공간을 yourCtrl 컨트롤러로 제어하겠다는 의미)
	myApp.controller("yourCtrl", function($scope){
		console.log("yourCtrl 에 있는 함수 호출됨");
		console.log($scope); //scope 객체보기
		$scope.nick="해골";
		//페이지 로딩 시점에 $scope object 에 함수 정의(저장)하기
		$scope.onMouseover=function(){
			alert("마우스 올렸구나?");
		}
	});
</script>
</head>
<body ng-app="myApp">
	<div ng-controller="myCtrl">
		<h3>myCtrl 컨트롤러가 관리하는 영역</h3>
		<!-- ng-click="nick='개구라'" 이런 식으로 nick의 모델의 값을 바꾸면 모델을 사용하는 view 부분({{nick}})이 자동으로 반영되어서 표현된다. -->
		<p>별명 : <strong>{{nick}}</strong></p>
		<!-- $scope.nick='개구라'; 이거랑 같은 뜻 -->
		<button ng-click="nick='개구라'">눌러봐!</button>
		<!-- $scope.btnClicked(); 이거랑 같은 뜻(즉 btnClicked() 함수가 호출되려면 scope 영역에 먼저 btnClicked() 함수를 정의해놓아야한다. -->
		<button ng-click="btnClicked()">눌러봐!</button>
	</div>
	<div ng-controller="yourCtrl">
		<!-- ng-init="nick='해골'"는 $scope.nick="해골";와 같은 의미이다. -->
		<h3 ng-init="nick='해골'">yourCtrl 컨트롤러가 관리하는 영역</h3>
		<p>별명 : <strong ng-bind="nick"></strong></p>
		<button ng-click="nick='피해골'">눌러봐!</button>
		<!-- 다양한 이벤트 처리 가능(ng-mouseout, ng-focus, ng-blur, ng-change, ng-input, ng-mouseover 등) -->
		<button ng-mouseover="onMouseover()">마우스 올림 체크!</button>
	</div>
</body>
</html>