<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.do</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<style>
	/* 글 내용을 출력할 div 에 적용할 css */
	.contents{
		width: 100%;
		border: 1px dotted #cecece;
	}
	/* 댓글 프로필 이미지를 작은 원형으로 만든다. */
	#profileImage{
		width: 50px;
		height: 50px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	/* ul 요소의 기본 스타일 제거 */
	.comments ul{
		padding: 0;
		margin: 0;
		list-style-type: none;
	}
	.comments dt{
		margin-top: 5px;
	}
	.comments dd{
		margin-left: 50px;
	}
	.comment_form textarea, .comment_form button,
		.comment-insert-form textarea, .comment-insert-form button{
		float: left;
	}
	.comments li{
		clear: left;
	}
	.comments ul li{
		border-top: 1px solid #888;
	}
	.comment_form textarea, .comment-insert-form textarea{
		width: 85%;
		height: 100px;
	}
	.comment_form button, .comment-insert-form button{
		width: 15%;
		height: 100px;
	}
	/* 댓글에 댓글을 다는 폼은 일단 숨긴다. */
	.comments form{
		display: none;
	}
	
	.comments li{
		position: relative;
	}
	.comments .reply_icon{
		position: absolute;
		top: 1em;
		left: 1em;
		color: red;
	}	
	pre {
	  display: block;
	  padding: 9.5px;
	  margin: 0 0 10px;
	  font-size: 13px;
	  line-height: 1.42857143;
	  color: #333333;
	  word-break: break-all;
	  word-wrap: break-word;
	  background-color: #f5f5f5;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	}
	/* 글 내용중에 이미지가 있으면 최대 폭을 100%로 제한하기 */
	.contents img{
		max-image: 100%;
	}
</style>
</head>
<body>
<div class="container">
	<c:if test="${not empty keyword }">
		<p class="alert alert-info">
			<strong>${keyword }</strong> 라는 키워드로 검색한 결과에 대한 자세히 보기 입니다.
		</p>
	</c:if>

	<c:if test="${dto.prevNum ne 0 }">
		<a class="btn btn-outline-info" href="detail.do?num=${dto.prevNum }&condition=${condition}&keyword=${encodedK}">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
			</svg>
			이전 글
		</a>
	</c:if>
	<c:if test="${dto.nextNum ne 0 }">
		<a class="btn btn-outline-info" href="detail.do?num=${dto.nextNum }&condition=${condition}&keyword=${encodedK}">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-down" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  			<path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
			</svg>
			다음 글
		</a>
	</c:if>
	<h1>글 상세 페이지</h1>
	<table>
		<tr>
			<th>글번호</th>
			<td>${dto.num }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${dto.regdate }</td>
		</tr>
	</table>
	<div class="contents">${dto.content }</div>
	<a href="list.do">목록 보기</a>
	<%-- 
		글 작성자와 로그인 된 아이디가 같을때만 기능을 제공해 준다. 
		즉, 본인이 작성한 글만 수정할수 있도록 하기 위해
	--%>
	<c:if test="${dto.writer eq id }">
		<a href="private/updateform.do?num=${dto.num }&condition=${condition }&keyword=${encodedK }">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-counterclockwise" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
				<path fill-rule="evenodd" d="M12.83 6.706a5 5 0 0 0-7.103-3.16.5.5 0 1 1-.454-.892A6 6 0 1 1 2.545 5.5a.5.5 0 1 1 .91.417 5 5 0 1 0 9.375.789z"/>
				<path fill-rule="evenodd" d="M7.854.146a.5.5 0 0 0-.708 0l-2.5 2.5a.5.5 0 0 0 0 .708l2.5 2.5a.5.5 0 1 0 .708-.708L5.707 3 7.854.854a.5.5 0 0 0 0-.708z"/>
			</svg>
			수정
		</a>
		<a href="javascript:deleteWriting()">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
			</svg>
			삭제
		</a>
	</c:if>
	
	<!-- 댓글 목록 -->
	<div class="comments">
		<ul>
			<c:forEach var="tmp" items="${commentList }">
				<c:choose>
					<c:when test="${tmp.deleted eq 'yes' }">
						<li>삭제된 댓글 입니다.</li>
					</c:when>
					<c:otherwise>
						<!-- 대댓글의 경우는 들여쓰기가 된 것처럼 보이도록 설정 -->
						<li <c:if test="${tmp.num ne tmp.comment_group }">style="padding-left:50px;"</c:if>>
							<c:if test="${tmp.num ne tmp.comment_group }">
								<svg class="reply_icon" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-return-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								  <path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
								  <path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/>
								</svg>
							</c:if>
							<dl>
								<dt>
									<c:choose>
										<c:when test="${empty tmp.profile }"> <!-- empty 연산자는 null or "" 인지 판별할 때 쓴다. -->
											<svg id="profileImage" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
												<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
											</svg>
										</c:when>
										<c:otherwise>
											<img id="profileImage" 
												src="${pageContext.request.contextPath}${requestScope.dto.profile}"/>
										</c:otherwise>
									</c:choose>
									<span>${tmp.writer }</span>
									<c:if test="${tmp.num ne tmp.comment_group }">
										@<strong>${tmp.target_id }</strong>
									</c:if>
									<span>${tmp.regdate }</span>
									<a href="javascript:" class="reply_link">답글</a>
									<c:if test="${tmp.writer eq id }">
										| <a href="javascript:deleteComment(${tmp.num })">삭제</a>
									</c:if>
								</dt>
								<dd>
									<pre>${tmp.content }</pre> <!-- tab, 띄어쓰기, 개행 등등을 해석해주는 pre 요소 -->
								</dd>
							</dl>
							<form action="private/comment_insert.do" class="comment-insert-form"
								method="post">
								<input type="hidden" name="ref_group" 
									value="${dto.num }"/>
								<input type="hidden" name="target_id" 
									value="${tmp.writer }"/>
								<input type="hidden" name="comment_group" 
									value="${tmp.comment_group }"/>
								<textarea name="content"></textarea>
								<button type="submit">등록</button>
							</form>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div>
	
	<div class="comment_form">
		<!-- 원글에 댓글을 작성하는 form -->
		<form action="private/comment_insert.do" method="post">
			<!-- 원글의 글번호가 ref_group 번호가 된다. -->
			<input type="hidden" name="ref_group" value="${dto.num }" />
			<!--  원글의 작성자가 댓글의 수신자가 된다. -->
			<input type="hidden" name="target_id" value="${dto.writer }" />
			<textarea name="content"><c:if test="${empty id }">로그인이 필요합니다.</c:if></textarea>
			<button type="submit">등록</button>
		</form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<script>
	function deleteComment(num){
		var isDelete=confirm("댓글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="${pageContext.request.contextPath}"+
			"/cafe/private/comment_delete.do?num="+num+"&ref_group=${dto.num}";
		}
	}

	//답글 달기 링크를 클릭했을 때 실행할 함수 등록
	$(".reply_link").on("click", function(){
		//로그인 여부
		var isLogin = ${not empty id};
		if(isLogin == false){
			alert('로그인 페이지로 이동합니다')
			location.href="${pageContext.request.contextPath}/users/loginform.do?"+
					"url=${pageContext.request.contextPath}/cafe/detail.do?num=${dto.num}";
		}
		
		$(this).parent().parent().parent().find(".comment-insert-form")
		.slideToggle();
		if($(this).text()=="답글"){ //링크 text를 답글일 때 클릭하면
			$(this).text("취소"); //취소로 바꾸고
		}else{ //취소일 때 클릭하면
			$(this).text("답글"); //답글로 바꾼다.
		}
	});

	$(".comment_form form").on("submit", function(){
		//로그인 여부
		var isLogin = ${not empty id};
		if(isLogin == false){
			alert('로그인 페이지로 이동합니다')
			location.href="${pageContext.request.contextPath}/users/loginform.do?"+
					"url=${pageContext.request.contextPath}/cafe/detail.do?num=${dto.num}";
			return false; //폼 전송 막기
		}
	});

	function deleteWriting(){
		alert("정말로 삭제하시겠습니까?");
		location.href="private/delete.do?num=${dto.num }";
	}
</script>
</body>
</html>





