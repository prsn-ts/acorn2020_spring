package com.gura.spring05.cafe.dao;

import java.util.List;

import com.gura.spring05.cafe.dto.CafeCommentDto;

public interface CafeCommentDao {
	//댓글 목록 얻어오기
	public List<CafeCommentDto> getList(int ref_group);
	//댓글 삭제
	public void delete(int num);
	//댓글 추가
	public void insert(CafeCommentDto dto);
	//추가할 댓글의 글번호를 리턴하는 메소드
	public int getSequence(); //insert하는 시점에 원글의 대한 댓글(대댓글아님)에 num 칼럼과, comment_group 칼럼에 동일한 값을 집어넣기위함.(생성한 시퀀스 값을 가져와서 쓴다.)
	//댓글 수정
	public void update(CafeCommentDto dto);
}
