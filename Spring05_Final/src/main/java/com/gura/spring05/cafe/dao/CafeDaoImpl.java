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
	public boolean delete(int num) {
		int sqlresult = session.delete("cafe.delete", num);
		if(sqlresult <= 0) {
			System.out.println("삭제된 것이 없습니다.");
			return false;
		}else {
			System.out.println("삭제된 것이 있습니다!!");
			return true;
		}
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
	//전체 댓글의 개수를 가져오는 메소드
	@Override
	public int getCount(int ref_group) {
		
		return session.selectOne("cafeComment.getCount", ref_group);
	}
}
