package com.gura.spring04.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.todo.dto.TodoDto;
import com.gura.spring04.todo.service.TodoService;

@Controller //component-scan 시점에 객체 생성.
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	//할일 추가 폼 요청 처리
	@RequestMapping("/todo/insertform.do")
	public String insertform() {
		//수행할 비즈니스 로직이 없다.
		
		//view page로 forward 이동해서 응답.
		return "todo/insertform";
	}
	
	//할일 추가 요청 처리
	@RequestMapping("/todo/insert.do")
	public String insert(@ModelAttribute TodoDto dto) {
		//회원 정보를 DB에 저장하고
		service.addTodo(dto);
		//view page로 forward 이동해서 응답.
		return "todo/insert";
	}
	
	//할일 목록 보기 요청 처리
	@RequestMapping("/todo/list")
	public ModelAndView list(ModelAndView mView) {
		//비즈니스 로직은 service에서 수행한다.
		service.getListTodo(mView);
		//view 페이지의 정보를 담고
		mView.setViewName("todo/list");
		//mView 객체를 넘기면서 view 페이지로 이동
		return mView;
	}
}
