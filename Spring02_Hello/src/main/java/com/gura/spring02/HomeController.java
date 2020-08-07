package com.gura.spring02;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러를 만들때 사용하는 어노테이션
@Controller
public class HomeController {
	
	//해당 메소드가 어떤 요청을 처리할지 결정하는 어노테이션
	@RequestMapping("/home.nhn") //.nhn 생략가능(왜냐면 web.xml 문서에 .nhn으로 끝나는 파일만 DispatcherServlet이 요청을 받아서 해당 controller에 넘겨주기 때문에 .nhn을 안붙혀도된다.)
	public String home() {
		
		//forward 이동될 jsp 페이지의 위치를 리턴해 준다.
		// "/WEB-INF/views" + home + ".jsp"
		return "home"; //앞에 "/WEB-INF/views"와 맨 뒤에 ".jsp"는 자동으로 붙는다.
	}
	
}
