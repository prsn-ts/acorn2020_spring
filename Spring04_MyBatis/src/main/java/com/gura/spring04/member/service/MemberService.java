package com.gura.spring04.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dto.MemberDto;

//컨트롤러의 비즈니스 로직 수행 부분을 전담해서 맡을 service interface
public interface MemberService {
	public void addMember(MemberDto dto);
	public void updateMember(MemberDto dto);
	public void deleteMember(int num);
	public void getMember(int num, ModelAndView mView);
	public void getListMember(ModelAndView mView);
}
