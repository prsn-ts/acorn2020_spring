package com.gura.spring05.users.dao;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersDao {
	public boolean isExist(String inputId);
	public void insert(UsersDto dto);
	//로그인 정보가 유효한지 판단하는 메소드
	public boolean isValid(UsersDto dto);
	//로그인한 회원의 정보를 가져오는 메소드
	public UsersDto getData(String id);
}
