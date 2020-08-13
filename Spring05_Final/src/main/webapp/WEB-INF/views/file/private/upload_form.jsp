<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/private/upload_form.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>파일 업로드 폼 입니다.</h1>
	<%--
		[ 파일 업로드 폼 작성법 ]
		1. method="post"
		2. 폼에 enctype="multipart/form-data" 속성 추가
		3. <input type="file" /> 을 이용한다. 
	--%>
	<%-- 폼 제출 시 파일 이름(제목), 파일 데이터 등이 전송됨--%>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목(설명)</label>
			<input class="form-control" type="text" name="title" id="title" /><br />
		</div>
		<div class="form-group">
			<label for="myFile">첨부 파일</label>
			<input class="form-control" type="file" name="myFile" id="myFile" /><br />
		</div>
		<button class="btn btn-outline-primary" type="submit">업로드</button>
	</form>
</div>
</body>
</html>