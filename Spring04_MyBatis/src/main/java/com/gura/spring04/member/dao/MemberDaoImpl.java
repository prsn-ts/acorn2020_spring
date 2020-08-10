package com.gura.spring04.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.member.dto.MemberDto;

//Component scan 을 했을 때 bean 이 되도록 어노테이션을 붙인다.
//dao는 데이터를 저장 및 관리하는 의미에서 @Repository라고 생각하면된다
//@Repository는 dao 를 bean 으로 만들기 위한 어노테이션(spring 이 관리하는 객체를 만들기 위한(주입받기 위한) 어노테이션)
@Repository // 이 어노테이션 때문에 new MemberDaoImpl() 한 듯이 객체를 bean으로부터 주입받는다.
public class MemberDaoImpl implements MemberDao {
	
	/*
		spring 이 관리 하고 있는 객체를 주입(Dependency Injection) 받으려면 @Autowired 어노테이션을 붙인다.
		spring 이 관리 하고 있는 객체 중에서 SqlSession type 이 존재하면 자동 주입된다.(@Autowired의 역할)
		@Autowired는 런타임시에 bean에 존재하는 객체(SqlSession 등등)를 내부적으로 setter 또는 getter를 만들어서 값을 저장 및 추출한다
	*/
	//의존 객체 주입 받기(Dependency Injection)
	@Autowired //이 키워드로인해 MemberDaoImpl 클래스 안에서 SqlSession 객체가 필요한 시점(밑에 insert, update, delete 등의 메소드가 호출되기 바로 직전)에 주입된다.
	private SqlSession session; //servlet-context.xml에서 SqlSession의 객체를 생성해서 bean container가 관리한다.
	
	@Override
	public void insert(MemberDto dto) {
		/*
		 *  mapper namespace -> member
		 *  sql id -> insert
		 *  parameterType -> MemberDto
		 */
		session.insert("member.insert", dto);
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 *  mapper namespace -> member
		 *  sql id -> update
		 *  parameterType -> MemberDto
		 */
		session.update("member.update", dto);
	}

	@Override
	public void delete(int num) {
		/*
		 *  mapper namespace -> member
		 *  sql id -> delete
		 *  parameterType -> int or java.lang.Integer (parameterType을 int로 써도 자동으로 java.lang.Integer 이런식으로 변경이된다)
		 */
		session.delete("member.delete", num);
	}

	@Override
	public MemberDto getData(int num) { //DB에서 select 된 row의 개수가 1개일 때
		/*
		 *  mapper namespace -> member
		 *  sql id -> getData
		 *  parameterType -> int
		 *  resultType -> MemberDto //결과를 담는 데이터 타입.
		 *  - resultType 은 select 된 row 하나를 담을 데이터 type을 의미한다.
		 *  - selectOne() 메소드는 resultType 을 리턴 해준다.
		 */
		MemberDto dto = session.selectOne("member.getData", num); //row가 하나인 경우 selectOne() 함수 사용.
		return dto;
	}

	@Override
	public List<MemberDto> getList() { //DB에서 select 된 row의 개수가 여러 개일 때
		//selectList() 메소드는 List type를 반환하고 List의 제너릭 타입이 resultType이 된다.
		/*
		 *  resultType -> MemberDto
		 *  - resultType 은 select 된 row 하나를 담을 데이터 type을 의미한다.
		 *  - selectList() 메소드는 List type 을 리턴하고 List 의 generic type이 resultType 이 된다.
		 */
		List<MemberDto> list = session.selectList("member.getList");
		return list;
	}
	
}
