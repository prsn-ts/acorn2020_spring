package com.gura.spring05.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.cafe.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	@Autowired
	private SqlSession session;

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		return session.selectList("cafe.getList", dto);
	}

	@Override
	public int getCount(CafeDto dto) {
		return session.selectOne("cafe.getCount", dto);
	}

	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert", dto);
	}

	@Override
	public CafeDto getData(int num) {
		return session.selectOne("cafe.getData", num);
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafe.addViewCount", num);
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete", num);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update", dto);
	}

	//검색 키워드와 글 번호를 이용해서 나온 검색 결과에 대한 이전글, 다음글 처리하는 메소드
	//키워드가 들어있는 CafeDto 를 전달받아서 글 정보를 리턴하는 메소드
	@Override
	public CafeDto getData(CafeDto dto) {
		return session.selectOne("cafe.getData2", dto);
	}
}
