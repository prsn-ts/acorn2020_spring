<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/updateform.jsp</title>
<!-- css 코드 적는 순서(순서 맞춰서 적어야 오류없음)
	1. 외부 css 파일
	2. 커스텀 css
 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<style>
	/* 프로필 업로드 폼을 화면에서 숨긴다. */
	#profileForm{
		display:none;
	}
	/* 이미지를 작은 원형으로 만든다. */
	#profileImage{
		width: 50px;
		height: 50px;
		border: 1px solid #cecece;
		border-radius: 50%;
		curser: pointer;
	}
</style>
</head>
<body>
<div class="container">
	<h1>회원정보 수정 폼 입니다.</h1>
	<%-- img 태그에 focus를 주기위해 a태그로 감싸고 javascript를 아무것도 실행하지 않는 구문을 넣어서 페이지 이동없이 포커스만 잡힐 수 있게 처리(키보드로 사용하는 사람을 위한 배려) --%>
	<a href="javascript:" id="profileLink">
		<c:choose>
			<c:when test="${empty dto.profile }"> <!-- empty 연산자는 null or "" 인지 판별할 때 쓴다. -->
				<svg id="profileImage" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
				</svg>
			</c:when>
			<c:otherwise>
				<img id="profileImage" 
					src="${pageContext.request.contextPath}${requestScope.dto.profile}"/>
			</c:otherwise>
		</c:choose>
	</a>
	<form action="update.do" method="post">
		<%-- 프로필 이미지를 DB에 저장하기 위해 hidden type으로 설정. --%>
		<input type="hidden" name="profile" id="profile" value="${dto.profile }"/> <!-- el문법 기준으로 dto의 profile 값이 null 일 경우에 value="" -> 이런식의 빈 문자열을 보여준다. -->
		<div class="form-group">
			<label for="id">아이디</label>
			<input class="form-control" type="text" id="id" value="${dto.id }" disabled />
		</div>
		<div class="form-group">
			<label for="email">이메일</label>
			<input class="form-control" type="text" id="email" name="email" value="${dto.email }" />
		</div>
		<button type="submit">수정확인</button>
		<button type="reset">취소</button>
	</form>
	
	<form action="profile_upload.do" method="post" 
		enctype="multipart/form-data" id="profileForm">
		<input type="file" name="image"
			accept=".jpg, .jpeg, .png, .JPG, .JPEG" id="image"/>
	</form>
</div>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>
<script>
	//프로필 링크를 클릭했을 때 프로필 수정에 대해 실행할 함수 등록
	$("#profileLink").on("click", function(){
		//프로필 이미지를 눌렀을 때 input type="file" 을 강제 클릭하게 한다.
		$("#image").click(); //파일 선택창이 뜰 수 있도록
	});
	
	//이미지를 선택했을 때 변화를 감지되었을 때 동작할 함수 등록
	$("#image").on("change", function(){ //기존 파일이 변경된 경우
		//폼을 강제 제출한다.
		$("#profileForm").submit();
	});
	
	//폼이 ajax 로 제출될 수 있도록 플러그인을 동작 시킨다.
	$("#profileForm").ajaxForm(function(data){
		/*
			img 태그의 ajax의 결과로 src 속성을 대입해야하는데 기존 이미지가 벡터 이미지를 출력하는 svg 요소이기 때문에 src 속성에 값을 대입할 수 없어서 선택한 이미지로 안바뀌는 현상이 발생함
			따라서 기존의 요소를 제거하고 img 태그에 src 속성을 붙인 요소 자체가 추가되도록 자바스크립트로 설정한다.
		*/
		//기존 프로필 이미지 요소를 제거한다.
		$("#profileImage").remove();
		
		//프로필 이미지를 업데이트 한다. data => {imageSrc:"/upload/xxx.jpg"}
		//새로 img 요소를 만들어서 #profileLink 에 추가한다.
		$("<img/>")
		.attr("id", "profileImage")
		.attr("src", "${pageContext.request.contextPath}"+data.imageSrc)
		.appendTo("#profileLink");
		
		//회원정보 수정폼 전송될 때 같이 프로필 정보도 같이 전송 되도록 한다.
		$("#profile").val(data.imageSrc); // input type="hidden"의 value값
	})
</script>
</body>
</html>