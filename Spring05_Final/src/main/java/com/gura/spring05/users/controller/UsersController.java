package com.gura.spring05.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	/*
	//개인정보 보기 요청 처리(info.do 요청 처리)
	@RequestMapping("users/private/info.do")
	public ModelAndView info(@ModelAttribute UsersDto dto, HttpSession session, ModelAndView mView) {
		//로그인된 아이디의 정보를 읽어온다.
		String id = (String)session.getAttribute("id");
		//비즈니스 로직은 service에서 수행한다.
		service.getData(dto, id, mView);
		// view 페이지로 forward 이동해서 응답하기
		mView.setViewName("users/private/info");
		return mView;
	}
	*/
	//개인정보 보기 요청 처리(info.do 요청 처리)
	@RequestMapping("users/private/info.do")
	public ModelAndView info(HttpServletRequest request, ModelAndView mView) {
		service.getInfo(request.getSession(), mView);
		mView.setViewName("users/private/info");
		return mView;
	}
	
	//회원 가입 폼 요청 처리
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		
		// /WEB-INF/views/users/signup_form.jsp 페이지로 forward 이동해서 응답.
		return "users/signup_form";
	}
	
	//아이디가 존재하는 지 여부를 처리하는 요청처리
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){
		//service 가 리턴해주는 Map 객체를 리턴한다.
		return service.isExistId(inputId);
	}
	
	//회원 가입 요청 처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {
		//service 객체를 이용해서 사용자 정보를 추가한다.
		service.addUser(dto);
		// view 페이지로 forward 이동해서 응답하기
		mView.setViewName("users/signup");
		return mView;
	}
	
	@RequestMapping("users/loginform")
	public String loginform(HttpServletRequest request) {
		//url 파라미터가 넘어오는지 읽어와 보기
		String url = request.getParameter("url");
		if(url==null){//목적지 정보가 없다면
			String cPath=request.getContextPath();
			url=cPath+"/home.do"; //로그인 후 인덱스 페이지로 가도록 하기 위해
		}
		//request 에 담는다.
		request.setAttribute("url", url);
		return "users/loginform";
	}
	
	//회원 가입 요청 처리
	@RequestMapping("users/login")
	public ModelAndView login(UsersDto dto, ModelAndView mView,
			HttpSession session, HttpServletRequest request) {
		//service에 넘겨주어야할 인자값들
		//1. UsersDto dto
		//2. ModelAndView mView
		//3. HttpSession
		//로그인 후 가야하는 목적지 정보
		String url = request.getParameter("url");
		//목적지 정보도 미리 인코딩 해 놓는다.
		String encodedUrl = URLEncoder.encode(url);
		//view 페이지에 전달하기 위해 ModelAndView 객체에 담아준다.
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		//service 객체를 이용해서 로그인 처리를 한다.
		service.loginProcess(dto, mView, session);
		// view 페이지로 forward 이동해서 응답하기
		mView.setViewName("users/login");
		return mView;
	}
	
	//로그아웃 처리.
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.do";
	}
}
