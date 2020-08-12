package com.gura.spring05.users.dao;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersDao {
	public boolean isExist(String inputId);
	public void insert(UsersDto dto);
	//로그인 정보가 유효한지 판단하는 추상 메소드
	public boolean isValid(UsersDto dto);
	//로그인한 회원의 정보를 가져오는 추상 메소드
	public UsersDto getData(String id);
	//탈퇴 처리하는 추상 메소드
	public void delete(String id);
	//개인정보 수정 요청 처리하는 추상 메소드
	public void update(UsersDto dto);
	//비밀번호 수정 요청 처리하는 추상 메소드
	public boolean updatePwd(UsersDto dto);
}
