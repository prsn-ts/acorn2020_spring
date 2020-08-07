package com.gura.spring04.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;

/*
 *  비즈니스 로직 처리할 서비스 클래스 정의하기
 */

//bean 이 되도록 @service 어노테이션을 붙인다.
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	//의존 객체 주입 받기
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
	}
	//회원 정보를 수정하는 비즈니스 로직 처리
	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
	}
	//회원 정보를 삭제하는 비즈니스 로직 처리
	@Override
	public void deleteMember(int num) {
		dao.delete(num);
	}
	//회원 한명의 정보를 불러와서 ModelAndView 객체에 담아주는 비즈니스 로직 처리
	@Override
	public void getMember(int num, ModelAndView mView) {
		//수정할 회원의 정보를 얻어온다.
		MemberDto dto = dao.getData(num);
		//model 을 ModelAndView 객체에 담는다.
		mView.addObject("dto", dto);
	}
	//회원 목록을 불러와서 ModelAndView 객체에 담아주는 비즈니스 로직 처리
	@Override
	public void getListMember(ModelAndView mView) {
		List<MemberDto> list = dao.getList();
		mView.addObject("list", list);
	}

}
