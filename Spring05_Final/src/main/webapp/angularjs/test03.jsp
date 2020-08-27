<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test03.jsp</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<!-- angularjs 로딩 시키기 -->
<script src="../resources/js/angular.min.js"></script>
</head>
<!-- ng-app 속성을 통해 body 에서 일어나는 일은 angular 로 관리를 하겠다! 라는 의미 -->
<body ng-app>
<div class="container">
	<!-- $scope.a='btn-primary' or scope 영역안에 {a:'btn-primary'} 이런 식으로 저장되어있다. -->
	<h1 ng-init="a='btn-primary'">클래스 속성 조작하기</h1>
	<!-- 
		입력 혹은 선택하는 요소들은 앵귤러가 관리하는 model로 만들 수 있다.
		또한  ng-model="b" 이렇게 입력한 내용은 $scope.b 이런식의 model이 만들어지고
		{{b}} 이런 식으로 쓰면 바로바로 적용이 되기 때문에 편하다는 장점이 있다.
	-->
	<input type="text" ng-model="b" /><br />
	<button class="btn btn-primary">버튼1</button>
	<button class="btn {{a}}">버튼2</button>
	<button class="btn {{b}}">버튼3</button>
	<!-- ng-class의 배열에 추가하고 싶은 클래스들(['btn', 'btn-primary'])을 넣으면 class="btn btn-primary" 이렇게 추가가 된다. -->
	<button ng-class="['btn', 'btn-primary']">버튼4</button>
	<button ng-class="{'btn':true, 'btn-primary':true}">버튼5</button>
	<br />
	<!-- checkbox로 model을 사용하면 그 변수안에는 동적으로 true or false가 들어간다. -->
	<input type="checkbox" ng-model="isLarge" />
	<button class="btn btn-success" ng-class="{'btn-lg':isLarge}">버튼6</button>
</div>
</body>
</html>