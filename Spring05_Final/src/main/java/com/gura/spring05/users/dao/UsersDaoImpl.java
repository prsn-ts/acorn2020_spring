package com.gura.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.users.dto.UsersDto;
/*
 *  Dao 에 붙인 @Repository 라는 어노테이션은
 *  DB 관련 작업을 하다가 SQLException 이 발생하는 경우 해당 Exception 대신에
 *  DataAccessException 을 발생 시킨다.
 *  따라서 DB 관련 작업을 하다가 발생하는 예외는 예외 컨트롤러에서 처리를 하면된다.
 */

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
	/* DB에 비밀번호 암호화 처리로 인해 사용안함.
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
	*/
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
	//탈퇴 처리하는 메소드
	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}
	//개인정보 수정하는 메소드
	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}
	/* DB에 비밀번호 암호화 처리로 인해 사용안함.
	@Override
	public boolean updatePwd(UsersDto dto) {
		//update 문의 영향을 받은 row 의 개수가 리턴된다.
		int result = session.update("users.updatePwd", dto);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	*/
	@Override
	public void updatePwd(UsersDto dto) {
		//update 문의 영향을 받은 row 의 개수가 리턴된다.
		session.update("users.updatePwd", dto);
	}
}
