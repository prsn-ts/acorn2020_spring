package com.gura.spring04.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dto.MemberDto;
import com.gura.spring04.todo.dao.TodoDao;
import com.gura.spring04.todo.dto.TodoDto;

/*
 *  비즈니스 로직 처리할 서비스 클래스 정의하기
 */

//bean이 되도록 service 어노테이션을 붙인다.
@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoDao dao;

	@Override
	public void addTodo(TodoDto dto) {
		dao.insert(dto);
	}
	//할일 정보를 수정하는 비즈니스 로직 처리
	@Override
	public void updateTodo(TodoDto dto) {
		//업데이트 로직 수행
		dao.update(dto);
	}

	@Override
	public void deleteTodo(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//할일 하나의 정보를 불러와서 ModelAndView 객체에 담아주는 비즈니스 로직 처리
	public void getTodo(int num, ModelAndView mView) {
		//수정할 회원의 정보를 얻어온다.
		TodoDto dto = dao.getData(num);
		//model 을 ModelAndView 객체에 담는다.
		mView.addObject("dto", dto);
	}

	@Override
	public void getListTodo(ModelAndView mView) {
		//dao의 리스트 목록을 list 변수에 저장.
		List<TodoDto> list = dao.getList();
		//ModelAndView 객체에 list 변수 값을 저장.
		mView.addObject("list", list);
	}

}
