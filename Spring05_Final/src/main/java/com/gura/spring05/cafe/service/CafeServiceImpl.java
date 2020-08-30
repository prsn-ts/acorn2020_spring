package com.gura.spring05.cafe.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring05.cafe.dao.CafeCommentDao;
import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.exception.NotDeleteException;

@Service
public class CafeServiceImpl implements CafeService{
	@Autowired
	private CafeDao cafeDao;
	@Autowired
	private CafeCommentDao cafeCommentDao;
	
	//한 페이지에 나타낼 row 의 갯수
	final int PAGE_ROW_COUNT=5; //프로젝트 상황에 맞게끔 변경가능
	//하단 디스플레이 페이지 갯수
	final int PAGE_DISPLAY_COUNT=5; //프로젝트 상황에 맞게끔 변경가능

	@Override
	public void getList(HttpServletRequest request) {
		
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		/*
			검색 키워드에 관련된 처리
		*/
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		if(keyword == null){//전달된 키워드가 없다면
			//condition, keyword의 파라미터값이 null이 찍히지 않도록 하기 위함.(파라미터로 넘어오는 값이 null로 찍힐 경우 문제가 생길 수도 있다고 함.)
			keyword = ""; //빈 문자열을 넣어준다.
			condition = ""; //빈 문자열을 넣어준다.
		}
		
		//keyword 변수의 내용을 파라미터로 전송할 때 인코딩된 키워드로 보내기 위함.
		//인코딩안된 내용을 파라미터로 보내면 문제가 발생할 수도 있다.
		//인코딩된 키워드를 미리 만들어 둔다.
		String encodedK = URLEncoder.encode(keyword);
		
		//keyword와 condition 변수에 null값이 들어오는지 확인용.
		//request.getParameter("keyword"), request.getParameter("keyword")의 값이 없는 경우 null값이 들어간다.
		System.out.println(keyword);
		System.out.println(condition);
		
		//검색 키워드와 startRowNum, endRowNum 을 담을 CafeDto 객체 생성
		CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		if(!keyword.equals("")){ //만일 키워드가 넘어온다면
			if(condition.equals("title_content")){
				//검색 키워드를 CafeDto 객체의 필드에 담는다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		//카페 목록 얻어오기
		List<CafeDto> list = cafeDao.getList(dto);
		//전체 row 의 개수를 담을 totalRow 변수.
		int totalRow = cafeDao.getCount(dto);
		
		//하단에 표시할 전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT); //double 연산으로 소수점이 나오는데 이것을 올림 연산(Math.ceil)을 해서 전체 행의 개수에 맞는 하단에 표시할 전체 페이지의 개수를 구하기 위함.
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면(실제 하단 페이지 개수(totalPageCount)보다 끝 페이지 번호 계산된 값(endPageNum)이 많다면)
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다.(실제 하단 페이지 개수로 화면에 출력될 수 있도록 endPageNum의 값을 totalPageCount로 넣어준다.)
		}
		
		//EL 에서 사용할 값을 미리 request 에 담아두기
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		//파라미터로 전달되는 글번호
		int num = Integer.parseInt(request.getParameter("num"));
		/*
		검색 키워드에 관련된 처리
		*/
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		if(keyword == null){//전달된 키워드가 없다면
			//condition, keyword의 파라미터값이 null이 찍히지 않도록 하기 위함.(파라미터로 넘어오는 값이 null로 찍힐 경우 문제가 생길 수도 있다고 함.)
			keyword = ""; //빈 문자열을 넣어준다.
			condition = ""; //빈 문자열을 넣어준다.
		}
		
		//keyword 변수의 내용을 파라미터로 전송할 때 인코딩된 키워드로 보내기 위함.
		//인코딩안된 내용을 파라미터로 보내면 문제가 발생할 수도 있다.
		//인코딩된 키워드를 미리 만들어 둔다.
		String encodedK = URLEncoder.encode(keyword);
		
		//keyword와 condition 변수에 null값이 들어오는지 확인용.
		//request.getParameter("keyword"), request.getParameter("keyword")의 값이 없는 경우 null값이 들어간다.
		System.out.println(keyword);
		System.out.println(condition);
		
		//글 번호와 검색 키워드를 담을 CafeDto 객체 생성
		CafeDto dto = new CafeDto();
		dto.setNum(num); //글 번호 담기
		
		if(!keyword.equals("")){ //만일 키워드가 넘어온다면
			if(condition.equals("title_content")){
				//검색 키워드를 CafeDto 객체의 필드에 담는다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		
		//키워드와 글 번호를 이용해 DB에 검색된 자세히 보여줄 글 정보
		CafeDto resultDto = cafeDao.getData(dto);
		//request 에 담기
		request.setAttribute("dto", resultDto);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		
		//글 조회수 올리기
		cafeDao.addViewCount(num);
		
		//한 페이지에 나타낼 댓글 의 갯수
		final int PAGE_ROW_COUNT=5;
		//하단 디스플레이 페이지 갯수
		final int PAGE_DISPLAY_COUNT=5;
		
		//전체 댓글 의 갯수를 읽어온다.
		int totalRow=cafeCommentDao.getCount(num);
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		//보여줄 페이지의 번호(만일 pageNum이 넘어오지 않으면 가장 마지막 페이지)
		String strPageNum = request.getParameter("pageNum");
		//일단 마지막 페이지의 댓글 목록을 보여주기로하고
		int pageNum=totalPageCount; //pageNum(보여줄 페이지 번호)이 넘어오지 않으면 가장 마지막 페이지(totalPageCount)를 보여준다.
		//만일 페이지 번호가 넘어온다면
		if(strPageNum!=null) {
			//넘어온 페이지에 해당하는 댓글 목록을 보여주도록 한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		// CafeDto 객체에 위에서 계산된 startRowNum 과 endRowNum 을 담는다.
		CafeCommentDto commentDto=new CafeCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		//ref_group 번호도 담는다.(놓쳤던 부분, 하나의 글에다가 댓글들을 나타내기위해서 글 번호도 dto에 저장한다.)
		commentDto.setRef_group(num);
		
		//원글의 글번호를 이용해서 댓글 목록을 얻어온다.
		List<CafeCommentDto> commentList = cafeCommentDao.getList(commentDto);
		
		//request 에 담아준다.
		request.setAttribute("commentList", commentList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		/* detail.jsp 페이지에서 totalPageCount만 쓰이기 때문에 나머지는 request 영역에 저장할 필요 없다.
		request.setAttribute("PAGE_ROW_COUNT", PAGE_ROW_COUNT);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startRowNum", startRowNum);
		request.setAttribute("endRowNum", endRowNum);
		request.setAttribute("totalRow", totalRow);
		*/
		request.setAttribute("totalPageCount", totalPageCount);
		
	}
	//카페에 새글 올리기 메소드
	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
	}
	//카페에 자신이 쓴 글 수정하는 메소드
	@Override
	public void updateContent(CafeDto dto) {
		cafeDao.update(dto);
	}
	//카페에 자신이 쓴 글 삭제하는 메소드
	@Override
	public void deleteWriting(int num, HttpServletRequest request) {
		cafeDao.delete(num);
	}

	@Override
	public void saveComment(HttpServletRequest request) {
		//댓글 작성자
		String writer = (String)request.getSession().getAttribute("id");
		//폼 전송되는 댓글의 정보 얻어내기
		int ref_group = Integer.parseInt(request.getParameter("ref_group"));
		String target_id = request.getParameter("target_id");
		String content = request.getParameter("content");
		/*
		 *  원글의 댓글은 comment_group 번호가 전송이 안되고
		 *  댓글의 댓글은 comment_group 번호가 전송이 된다.
		 *  따라서 null 여부를 조사하면 원글의 댓글인지 댓글의 댓글인지 판단할 수 있다.
		 */
		String comment_group = request.getParameter("comment_group");
		int seq = cafeCommentDao.getSequence();
		
		//저장할 댓글 정보를 dto에 담기
		CafeCommentDto dto = new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		if(comment_group==null) {//원글의 댓글인 경우
			//댓글의 글번호가 comment_group 번호가 된다.
			dto.setComment_group(seq);
		}else {//댓글의 댓글인 경우
			//폼 전송된 comment_group 번호를 숫자로 바꿔서 dto 에 넣어준다.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		//댓글 정보를 DB 에 저장한다.
		cafeCommentDao.insert(dto);
	}

	@Override
	public void deleteComment(HttpServletRequest request) {
		//GET 방식 파라미터로 전달되는 삭제할 댓글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		//세션에 저장된 로그인된 아이디
		String id=(String)request.getSession().getAttribute("id");
		//댓글의 정보를 얻어와서 댓글의 작성자와 같은지 비교 한다.
		String writer = cafeCommentDao.getData(num).getWriter();
		if(!writer.equals(id)) {
			throw new NotDeleteException("남의 댓글을 삭제할 수 없습니다.");
		}
		cafeCommentDao.delete(num);
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		cafeCommentDao.update(dto);
	}

	@Override
	public void moreCommentList(HttpServletRequest request) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int ref_group = Integer.parseInt(request.getParameter("ref_group"));
		
		CafeDto dto=cafeDao.getData(ref_group);
		request.setAttribute("dto", dto);

		/* 아래는 댓글 페이징 처리 관련 비즈니스 로직 입니다.*/
		final int PAGE_ROW_COUNT=5;

		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;

		//전체 row 의 갯수를 읽어온다.
		//자세히 보여줄 글의 번호가 ref_group  번호 이다. 
		int totalRow=cafeCommentDao.getCount(ref_group);
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);

		// CafeCommentDto 객체에 위에서 계산된 startRowNum 과 endRowNum 을 담는다.
		CafeCommentDto commentDto=new CafeCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		//ref_group 번호도 담는다.
		commentDto.setRef_group(ref_group);

		//DB 에서 댓글 목록을 얻어온다.
		List<CafeCommentDto> commentList=cafeCommentDao.getList(commentDto);
		//request 에 담아준다.
		request.setAttribute("commentList", commentList);
		request.setAttribute("totalPageCount", totalPageCount);		
	}

	@Override
	public List<CafeDto> getList2(HttpServletRequest request) {

		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		/*
			검색 키워드에 관련된 처리
		*/
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		if(keyword == null){//전달된 키워드가 없다면
			//condition, keyword의 파라미터값이 null이 찍히지 않도록 하기 위함.(파라미터로 넘어오는 값이 null로 찍힐 경우 문제가 생길 수도 있다고 함.)
			keyword = ""; //빈 문자열을 넣어준다.
			condition = ""; //빈 문자열을 넣어준다.
		}
		
		//keyword 변수의 내용을 파라미터로 전송할 때 인코딩된 키워드로 보내기 위함.
		//인코딩안된 내용을 파라미터로 보내면 문제가 발생할 수도 있다.
		//인코딩된 키워드를 미리 만들어 둔다.
		String encodedK = URLEncoder.encode(keyword);
		
		//keyword와 condition 변수에 null값이 들어오는지 확인용.
		//request.getParameter("keyword"), request.getParameter("keyword")의 값이 없는 경우 null값이 들어간다.
		System.out.println(keyword);
		System.out.println(condition);
		
		//검색 키워드와 startRowNum, endRowNum 을 담을 CafeDto 객체 생성
		CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		if(!keyword.equals("")){ //만일 키워드가 넘어온다면
			if(condition.equals("title_content")){
				//검색 키워드를 CafeDto 객체의 필드에 담는다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		//카페 목록 얻어오기
		List<CafeDto> list = cafeDao.getList(dto);
		
		return list;
	}

	@Override
	public Map<String, Object> getPagingList(HttpServletRequest request) {

		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//CafeDto 객체 생성
		CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//전체 row 의 개수를 담을 totalRow 변수.
		int totalRow = cafeDao.getCount(dto);
		
		//하단에 표시할 전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT); //double 연산으로 소수점이 나오는데 이것을 올림 연산(Math.ceil)을 해서 전체 행의 개수에 맞는 하단에 표시할 전체 페이지의 개수를 구하기 위함.
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면(실제 하단 페이지 개수(totalPageCount)보다 끝 페이지 번호 계산된 값(endPageNum)이 많다면)
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다.(실제 하단 페이지 개수로 화면에 출력될 수 있도록 endPageNum의 값을 totalPageCount로 넣어준다.)
		}
		//페이징 처리의 데이터를 담을 List<Map> 객체 생성.
		Map<String, Object> pagingMap = new HashMap<>();
		//페이징 처리에 필요한 데이터 담기
		pagingMap.put("totalRow", totalRow);
		pagingMap.put("totalPageCount", totalPageCount);
		pagingMap.put("startPageNum", startPageNum);
		pagingMap.put("endPageNum", endPageNum);
		pagingMap.put("pageNum", pageNum);
		
		return pagingMap;
	}
}
