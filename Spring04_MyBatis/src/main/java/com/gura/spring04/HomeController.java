package com.gura.spring04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //component scan을 하는 시점에 spring bean container에 HomeController 객체의 참조값을 저장한다.
public class HomeController {
	
	@RequestMapping("/home")
	public String home() {
		
		return "home";
	}
	
}
