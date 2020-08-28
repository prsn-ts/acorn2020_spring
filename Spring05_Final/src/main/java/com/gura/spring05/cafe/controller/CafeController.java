package com.gura.spring05.cafe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService cafeService;
	
	//카페 글 목록 보기 요청 처리
	@RequestMapping("/cafe/list")
	public ModelAndView getList(HttpServletRequest request,
			ModelAndView mView) {
		cafeService.getList(request);
		mView.setViewName("cafe/list");
		return mView;
	}
	//카페 글 자세히 보기 요청 처리
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(HttpServletRequest request,
			ModelAndView mView) {
		cafeService.getDetail(request);
		mView.setViewName("cafe/detail");
		return mView;
	}
	//카페 새글 작성 폼 요청 처리
	@RequestMapping("/cafe/private/insertform")
	public ModelAndView insertForm(ModelAndView mView) {
		
		mView.setViewName("cafe/insertform");
		return mView;
	}
	//카페 새글 작성하기 요청 처리
	@RequestMapping(value = "/cafe/private/insert", method=RequestMethod.POST)
	public ModelAndView insert(CafeDto dto, ModelAndView mView, HttpSession session) {
		//dto 에 글 작성자 담기
		String id = (String)session.getAttribute("id");
		dto.setWriter(id);
		cafeService.saveContent(dto);
		mView.setViewName("cafe/insert");
		return mView;
	}
	
	//카페 자신이 쓴 글 수정 폼 요청 처리
	@RequestMapping("/cafe/private/updateform.do")
	public ModelAndView updateForm(ModelAndView mView, HttpServletRequest request) {
		cafeService.getDetail(request);
		mView.setViewName("cafe/updateform");
		return mView;
	}
	
	//카페 자신이 쓴 글 수정하기 요청 처리
	@RequestMapping("/cafe/private/update")
	public ModelAndView update(CafeDto dto, ModelAndView mView) {
		cafeService.updateContent(dto);
		mView.setViewName("cafe/update");
		return mView;
	}
	
	//카페 자신이 쓴 글 삭제하기 요청 처리
	@RequestMapping("/cafe/private/delete")
	public String delete(@RequestParam int num, HttpServletRequest request) {
		cafeService.deleteWriting(num, request);
		return "redirect:/cafe/list.do";
	}
	
	//원글의 댓글 and 댓글의 댓글 추가하기 요청 처리
	@RequestMapping(value = "/cafe/private/comment_insert",
			method=RequestMethod.POST)
	public ModelAndView commentInsert(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		//새 댓글을 저장하고
		cafeService.saveComment(request);
		//보고있던 글 자세히 보기로 다시 리다일렉트 이동 시킨다.
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	//댓글 삭제 요청 처리
	@RequestMapping("/cafe/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		cafeService.deleteComment(request);
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	//댓글 수정 요청 처리
	@RequestMapping(value = "/cafe/private/comment_update.do",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentUpdate(CafeCommentDto dto){
		//댓글을 수정 반영하고
		cafeService.updateComment(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	//추가 댓글 요청 처리
	@RequestMapping(value = "/cafe/ajax_comment_list.do", method=RequestMethod.GET)
	public String ajaxCommentList(HttpServletRequest request) {
		cafeService.moreCommentList(request);
		return "cafe/ajax_comment_list";
	}
	
	//임시용 카페 글 목록보기 요청 처리
	@RequestMapping("/cafe/ajax_list")
	@ResponseBody
	public List<CafeDto> ajaxList(HttpServletRequest request){
		
		return cafeService.getList2(request);
	}
	
	//임시용 카페 글 목록보기 페이징 요청 처리
	@RequestMapping("/cafe/ajax_paging_list.do")
	@ResponseBody
	public List<Integer> ajaxPagingList(HttpServletRequest request){
		
		return cafeService.getPagingList(request);
	}
}
