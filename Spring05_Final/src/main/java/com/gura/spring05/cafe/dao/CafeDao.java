package com.gura.spring05.cafe.dao;

import java.util.List;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeDao {
	//글목록
	public List<CafeDto> getList(CafeDto dto);
	//글의 개수
	public int getCount(CafeDto dto);
	//글 추가
	public void insert(CafeDto dto);
	//글 정보 얻어오기
	public CafeDto getData(int num);
	//키워드를 활용한 글 정보 얻어오기
	public CafeDto getData(CafeDto dto); //메소드 오버로딩(다중 정의, 같은 이름으로 여러개를 정의) -> 인자값이 달라서 메소드 이름이 같아도 오류 X
	//조회수 증가 시키기
	public void addViewCount(int num);
	//글 삭제
	public boolean delete(int num);
	//글 수정
	public void update(CafeDto dto);
	//전체 댓글의 개수를 가져오는 메소드
	public int getCount(int ref_group);
}
