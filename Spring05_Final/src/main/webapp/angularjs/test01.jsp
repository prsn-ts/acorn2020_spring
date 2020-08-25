<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test01.jsp</title>
<!-- angularjs 로딩 시키기 -->
<script src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
</head>
<!-- webapp 에 동등한 위치에 있기 때문에 직접 실행(run)해도 상관없다. -->
<!-- 밑에 코드들은 서버쪽이 아닌 클라이언트 사이드(웹브라우저)에서 해석되어 출력해 보여지는 것.
	참고로 페이지소스보기 했을 때 보여지는 코드들은 서버가 클라이언트로 보내는 문자열이다.("검사"로 보는 것은 angularjs가 서버로부터 보낸 문자열을 해석한 결과가 보여진 것) -->
<!-- angularjs에 대해 
	1. ng-app, ng-model 이런 속성을 directive(지시어)라고 부른다.
	2. angular에서는 데이터의 저장(변수 저장)과 저장된 데이터를 화면에 출력 하는 부분이 연결돼있는 것 같은 느낌을 준다
	-> 밑에 예제를 보면 입력창에 입력하자마자 바로바로 화면에 출력되는 것을 볼 수 있다. 이런 연결성 때문에 angularjs를 꽤나 사용한다고한다.
 -->
<body ng-app>
<h1>hello angular js!</h1>
<input ng-model="msg" type="text" placeholder="메세지 입력..." />
<p>{{msg}}</p>
<h1 ng-init="friends=['김구라','해골','원숭이']">친구 목록 입니다.</h1>
<ul>
	<li ng-repeat="tmp in friends">{{tmp}}</li>
</ul>
<h1>체크박스를 체크 해 보세요</h1>
<input type="checkbox" ng-model="isShow" /> <strong>{{isShow}}</strong><br />
<img src="../resources/images/kim1.png" ng-show="isShow" alt="" />
</body>
</html>