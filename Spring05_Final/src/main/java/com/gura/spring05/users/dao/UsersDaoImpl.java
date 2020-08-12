package com.gura.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	@Autowired
	private SqlSession session;

	@Override
	public boolean isExist(String inputId) {
		//입력한 아이디가 존재하는지 id 를 select 해 본다.
		String id = session.selectOne("users.isExist", inputId);
		
		if(id==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public boolean isValid(UsersDto dto) {
		String id = session.selectOne("users.isValid", dto);
		//select된 아이디가 없으면
		if(id==null) {
			return false;
		}else {
			return true;
		}
	}
	/*
	//인자로 전달되는 id에 해당되는 사용자 정보를 리턴하는 메소드
	@Override
	public UsersDto getData(String id) {
		//SqlSession 객체에서 selectOne 함수를 통해 id에 해당하는 한명의 회원 정보를 가져온다.
		return session.selectOne("users.getData", id);
	}
	*/
	//인자로 전달되는 id에 해당되는 사용자 정보를 리턴하는 메소드(Teacher)
	@Override
	public UsersDto getData(String id) {
		//SqlSession 객체에서 selectOne 함수를 통해 id에 해당하는 한명의 회원 정보를 가져온다.
		UsersDto dto = session.selectOne("users.getData", id);
		return dto;
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}
}
