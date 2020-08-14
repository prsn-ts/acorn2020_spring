package com.gura.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

public interface CafeService {
	//카페 목록 보기 요청 관련 메소드
	public void getList(HttpServletRequest request);
}
