<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/todo/insertform.jsp</title>
</head>
<body>
<div class="container">
	<h1>할일정보 추가 폼 입니다.</h1>
	<form action="insert.do" method="post">
		<input type="text" name="work" placeholder="할일 입력..." />
		<button type="submit">추가</button>
	</form>
</div>
</body>
</html>