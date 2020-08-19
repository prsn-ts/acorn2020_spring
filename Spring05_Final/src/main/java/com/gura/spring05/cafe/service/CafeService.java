package com.gura.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeService {
	//카페 목록 보기 요청 관련 추상 메소드
	public void getList(HttpServletRequest request);
	//카페 글 자세히 보기 요청 관련 추상 메소드
	public void getDetail(HttpServletRequest request);
	//카페 새글 작성 요청 관련 추상 메소드
	public void saveContent(CafeDto dto);
	//카페 수정하기 요청 관련 추상 메소드
	public void updateContent(CafeDto dto);
	//카페 글 삭제하기 요청 관련 추상 메소드
	public void deleteWriting(int num, HttpServletRequest request);
	//새로운 댓글을 저장하는 추상 메소드
	public void saveComment(HttpServletRequest request); //댓글 저장
	//자신의 댓글을 삭제하는 추상 메소드
	public void deleteComment(HttpServletRequest request); //댓글 삭제
	//자신의 댓글을 수정하는 추상 메소드
	public void updateComment(CafeCommentDto dto); //댓글 수정
}
