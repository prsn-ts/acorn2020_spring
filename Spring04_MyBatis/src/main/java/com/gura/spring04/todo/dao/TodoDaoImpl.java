package com.gura.spring04.todo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.todo.dto.TodoDto;

@Repository //이 어노테이션으로 인해 bean container로부터 TodoDaoImpl 객체를 주입받는다.
public class TodoDaoImpl implements TodoDao {
	
	@Autowired //이 어노테이션으로 인해 필요한 시점(밑에 함수들이 호출되는 시점)에 객체를 주입받는다.
	private SqlSession session;

	@Override
	public void insert(TodoDto dto) {
		/*
		 *  mapper namespace -> todo
		 *  sql id -> insert
		 *  parameterType -> TodoDto
		 */
		session.insert("todo.insert", dto);
	}

	@Override
	public void update(TodoDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TodoDto getData(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TodoDto> getList() {
		
		//session 객체를 이용해서 DB에서 할일 목록을 가져온다.
		List<TodoDto> list = session.selectList("todo.getList");
		return list;
	}

}
