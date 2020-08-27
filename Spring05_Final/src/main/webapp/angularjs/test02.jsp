<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test02.jsp</title>
<!-- angularjs 로딩 시키기 -->
<script src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
</head>
<!-- 
	ng-app 속성을 통해 body 에서 일어나는 일은 angular 로 관리를 하겠다! 라는 의미.
	ng-app 지시어를 쓰면 따로 지정되지 않은 기본 모듈, 기본 컨트롤러를 쓰는 것.
 -->
<body ng-app>
<!-- 
	count 라는 이름의 모델을 만들고 초기값을 0으로 부여 하겠다! 라는 의미
	앵귤러가 관리하는 오브젝트안에 count라는 방을 만들고 0을 집어넣은 것을 말함 (var count=0 보단 {count:0}의 의미에 가깝다)
 -->
<!-- ng-init="count=0"의 의미는 $scope.count=0; 이것과 같고 세부적으로 말하자면 -> $scope 영역안에 {count:0} 이런 식으로 저장되어 있다 -->
<h1 ng-init="count=0">이벤트 처리</h1>
<!-- 버튼에 클릭 이벤트가 일어나면 count=count+1 이 수행된다.(정확히는 $scope.count=$scope.count+1; 라고 보면 된다.) -->
<button ng-click="count=count+1">눌러보셈</button>
<!-- 버튼에 클릭 이벤트가 일어나면 count=0; 이 수행된다.(정확히는 $scope.count=0; 라고 보면 된다.) -->
<button ng-click="count=0">리셋</button>
<!-- 
	<p>{{count}}</p>와 <p ng-bind="count"></p>의 의미는 같다. 전자는 줄인 표현.
	count 모델안에 있는 값을 출력하기
 -->
<!-- $scope.innerText = $scope.count; 대략 이런 의미와 같다. -->
<p>{{count}}</p>
<p ng-bind="count"></p>
</body>
</html>