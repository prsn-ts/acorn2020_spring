package com.gura.spring04.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;

@Controller
public class MemberController {
	//spring bean container에서 관리하는 객체 중 MemberDaoImpl의 의존객체를 주입 받도록 설정해주는 작업
	//나중에는 spring bean container에서 관리하는 객체 중에 타입이 겹치는 부분이 있어서 @Autowired("아이디값 입력") 이런 식으로 겹치는 부분을 식별할 아이디를 부여한다고함.
	@Autowired  
	private MemberDao dao;
	
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//회원 목록을 얻어온다.
		List<MemberDto> list = dao.getList(); //핵심 의존 객체가 주입되었다는 가정하에 dao.getList() 실행.
		//model 을 담는다.
		mView.addObject("list", list);
		//view 페이지 정보를 담고
		mView.setViewName("member/list");
		//ModelAndView 객체를 리턴해 준다.
		return mView;
	}
}
