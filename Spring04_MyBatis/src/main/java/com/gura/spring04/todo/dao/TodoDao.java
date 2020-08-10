package com.gura.spring04.todo.dao;

import java.util.List;

import com.gura.spring04.todo.dto.TodoDto;

//dao가 구현할 인터페이스를 미리 정의한다.
public interface TodoDao { //class로 만든 것을 지우고 interface로 만든다.(의존성을 낮추기 위해)
	public void insert(TodoDto dto);
	public void update(TodoDto dto);
	public void delete(int num);
	public TodoDto getData(int num);
	public List<TodoDto> getList();
}
