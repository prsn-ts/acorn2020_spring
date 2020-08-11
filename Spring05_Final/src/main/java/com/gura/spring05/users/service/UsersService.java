package com.gura.spring05.users.service;

import java.util.Map;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersService {
	//아이디가 중복되는지 검사하는 추상 메소드
	public Map<String, Object> isExistId(String inputId);
	//회원 가입 처리하는 추상 메소드
	public void addUser(UsersDto dto);
}
