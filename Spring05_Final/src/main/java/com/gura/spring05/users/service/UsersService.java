package com.gura.spring05.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersService {
	//아이디가 중복되는지 검사하는 추상 메소드
	public Map<String, Object> isExistId(String inputId);
	//회원 가입 처리하는 추상 메소드
	public void addUser(UsersDto dto);
	//로그인 처리하는 추상 메소드
	public void loginProcess(UsersDto dto, ModelAndView mView,
			HttpSession session);
	/*
	//로그인된 회원의 정보를 가져오기위한 메소드
	public void getData(UsersDto dto, String id, ModelAndView mView);
	*/
	//Teacher의 info.do 요청 관련 메소드
	public void getInfo(HttpSession session, ModelAndView mView);
	//탈퇴 요청 관련 추상 메소드
	public void deleteUser(HttpSession session);
	//프로필 업로드 관련 처리 추상 메소드
	public Map<String, Object> saveProfileImage(HttpServletRequest request,
			@RequestParam MultipartFile image);
	//개인정보 수정 요청 관련 추상 메소드
	public void updateUser(HttpSession session, UsersDto dto);
	//비밀번호 수정 요청 관련 추상 메소드
	public void updateUserPwd(HttpSession session, UsersDto dto,
			ModelAndView mView);
}
