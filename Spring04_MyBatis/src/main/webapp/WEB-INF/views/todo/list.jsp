<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/todo/list.jsp</title>
</head>
<body>
	<a href="insertform.do">할일 추가 폼</a>
	<h1>할일 목록 입니다.</h1>
	<table>
		<thead>
			<tr>
				<td>순서</td>
				<td>할일</td>
				<td>작성일자</td>
				<td>수정</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${list }">
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.work }</td>
					<td>${tmp.regdate }</td>
					<td><a href="updateform.do?num=${tmp.num }">수정하기</a></td>
					<td><a href="delete.do?num=${tmp.num }">삭제하기</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>