package com.gura.spring02;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller가 있으면 서버 실행시점에 없을 때와는 다르게 
//HttpServletRequest, HttpServletResponce, HttpSession 등의 객체를
//friends.nhn의 요청이 들어오면 대응하는 함수의 인자값으로 쓸 수 있다는 특징이 있다.
@Controller
public class FriendsController {
	
	/*
	 *  HttpServletRequest or HttpServletResponse or HttpSession
	 *  객체가 필요하면 메소드의 인자에 선언해 놓으면 자동으로 실행 시점에 참조값이 전달된다.(@Controller 덕분에 가능)
	 */
	
	@RequestMapping("/friends.nhn")
	public String friends(HttpServletRequest request) {
		//1. 친구 목록을 저장하는 비즈니스 로직을 수행한다.
		List<String> list = new ArrayList<String>();
		list.add("gura");
		list.add("bone");
		list.add("monkey");
		//2. 로직 처리 결과 데이터(model)를 request 영역에 담는다.
		request.setAttribute("list", list);
		//3. view 페이지(jsp) 페이지로 forward 이동해서 응답한다.
		return "friends";
	}
}
