<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<!-- animate css 로딩하기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.css"/>
</head>
<style>
	.animate__shakeX{
		animation-duration: 0.4s;
	}
	/* 위에 로딩한 animate.css에 정의된 heartBeat 이라는 키프레임을 재정의(커스터마이징) 할 수 있다.(간단한 커스터마이징은 javascript 없이 css만으로도 커버 가능 ) */
	#submitBtn:hover{
		animation-name: heartBeat;
		animation-duration: 0.4s;
		animation-timing-function: ease-out;
		animation-iteration-count: 1;
	}
</style>
<body>
<div class="container animate__animated animate__backInDown filpInX">
	<h1>로그인 폼</h1>
	<form action="login.do" method="post" id="loginForm">
		<%-- 원래 가려던 목적지 정보를 url 이라는 파라미터 명으로 가지고 간다 --%>
		<input type="hidden" name="url" value="${url }" />
		<div class="form-group">
			<label for="id">아이디</label>
			<input class="form-control animate__animated " type="text" name="id" id="id" />
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<input class="form-control" type="password" name="pwd" id="pwd" />
		</div>
		<button class="btn btn-primary" type="submit" id="submitBtn">로그인</button>
	</form>
</div>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<script>
	//폼에 submit 이벤트가 일어 났을 때 실행할 함수 등록(특정한 시점에 특정한 요소에 애니메이션 적용하기위해서는 jquery가 필요)
	$("#loginForm").on("submit", function(){
		//1. 아이디를 입력 했는지 검증
		var inputId=$("#id").val();
		if(inputId==""){
			$("#id")
			.parent()
			.addClass("animate__shakeX")
			.one("animationend", function(){
				$(this).removeClass("animate__shakeX");
			});
			return false; //폼 전송 막기
		}
		//2. 비밀번호를 입력 했는지 검증
		var inputPwd=$("#pwd").val();
		if(inputPwd==""){
			$("#pwd")
			.parent()
			.addClass("animate__shakeX")
			.one("animationend", function(){
				$(this).removeClass("animate__shakeX");
			});
			return false; //폼 전송 막기
		}
	});
</script>
</body>
</html>