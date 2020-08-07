package com.gura.spring02;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller이 있으면 서버실행시점에 없을 때와는 조금 다르게 자기가 필요한 것들을 사용할 수 있게 준비되도록(가령 HttpServletRequest, HttpServletResponse, HttpSession 등의 객체를 제공) 하는 역할을 한다.
@Controller
public class FortuneController {
	
	/*
	 *  HttpServletRequest or HttpServletResponse or HttpSession
	 *  객체가 필요하면 메소드의 인자에 선언해 놓으면 자동으로 실행 시점에 참조값이 전달된다.(@Controller 덕분에 가능)
	 */
	
	@RequestMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		//1. 오늘의 운세를 얻어오는 비즈니스 로직을 처리한다.
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		//2. 로직 처리결과 데이터(model) 을 request 영역에 담는다.
		request.setAttribute("fortuneToday", fortuneToday);
		//3. view 페이지(jsp) 페이지로 forward 이동해서 응답한다.
		return "fortune"; // "/WEB-INF/views/fortune.jsp"
	}
}
