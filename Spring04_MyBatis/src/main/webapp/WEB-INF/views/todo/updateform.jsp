<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/updateform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>할일 정보 수정 폼 입니다</h1>
	
	<form action="update.do" method="post">
		<input type="hidden" name="num" value="${dto.num }"/>
		<div class="form-group">
			<label for="num">순서</label>
			<input type="text" id="num" class="form-control" value="${dto.num }" disabled/>
		</div>
		<div class="form-group">
			<label for="name">할일</label>
			<input type="text" id="work" name="work" class="form-control"
				value="${dto.work }" />
		</div>
		<div class="form-group">
			<label for="addr">작성일자</label>
			<input type="text" id="regdate" name="regdate" class="form-control"
				value="${dto.regdate }" />
		</div>
		<button type="submit" class="btn btn-primary">수정확인</button>
		<button type="reset" class="btn btn-warning">취소</button>
	</form>
</div>
</body>
</html>