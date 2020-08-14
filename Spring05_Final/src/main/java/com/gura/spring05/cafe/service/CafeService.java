package com.gura.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeService {
	//카페 목록 보기 요청 관련 메소드
	public void getList(HttpServletRequest request);
	//카페 글 자세히 보기 요청 관련 메소드
	public void getDetail(HttpServletRequest request);
	//카페 새글 작성 요청 관련 메소드
	public void saveContent(CafeDto dto);
	//카페 수정하기 요청 관련 메소드
	public void updateContent(CafeDto dto);
}
