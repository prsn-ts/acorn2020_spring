package com.gura.spring03.users;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
	//회원 가입 폼 요청 처리
	@RequestMapping("/users/signup_form") // "/users/signup_form.do" 요청이 왔을 때를 말함
	public String signupForm() {
		//수행할 비즈니스 로직은 현재 없다.
		
		//view 페이지의 정보만 리턴 해준다.
		return "users/signup_form"; // "/WEB-INF/views/users/signup_form.jsp"를 의미하고 이것은 응답을 위임할 곳을 의미.
	}
	@RequestMapping("/users/signup")
	public String signup(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id+"|"+pwd);
		return "users/signup";
	}
	@RequestMapping("/users/signup2")
	public String signup2(@RequestParam String id, @RequestParam String pwd) { //요청 파라미터라는 의미에서 어노테이션으로 표시(생략가능)
		/*
		 *  @RequestParam 어노테이션과 함께 메소드의 인자를 선언하면
		 *  요청 파라미터가 자동 추출되어서 전달된다.
		 *  @RequestParam 은 생략가능
		 *  form 요소의 name 속성의 value 와 지역 변수의 이름이 같아야 한다.(그래야 파라미터값을 받는 변수로 인식한다)
		 *  
		 *  ex)
		 *  (form 부분) -> (파라미터 받는 부분)
		 *  name = "id" -> String id
		 *  name = "pwd" -> String pwd
		 *  
		 *  GET 방식 파라미터라면
		 *  
		 *  ?name=xxx -> String name
		 *  ?addr=xxx -> String addr
		 *  ?pageNum=xxx -> String pageNum
		 *  ?pageNum=xxx -> int pageNum (마치 Integer.parseInt(request.getParameter("pageNum")) 한 것 같이 자동으로 데이터 타입도 바꿔준다.)
		 */
		System.out.println(id+"|"+pwd);
		return "users/signup";
	}
	
	@RequestMapping("/users/signup3")
	public String signup3(@ModelAttribute UsersDto dto) {
		/*
		 *  @ModelAttribute 어노테이션과 함께 dto 를 선언해 놓으면
		 *  전송된 파라미터가 추출되고 dto 에 담겨서 전달된다.
		 *  @ModelAttribute 는 생략 가능
		 *  form 요소의 name 속성의 value 와 dto 의 필드명이 같아야 한다.
		 *  GET 방식 전송이라면 파라미터명과 dto 의 필드명이 같아야 한다.
		 */
		System.out.println(dto.getId()+"|"+dto.getPwd());
		return "users/signup";
	}
}
