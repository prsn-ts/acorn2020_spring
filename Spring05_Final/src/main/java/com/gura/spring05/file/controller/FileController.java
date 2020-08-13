package com.gura.spring05.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.file.dto.FileDto;
import com.gura.spring05.file.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;

	//파일 목록 보기 요청 처리 
	@RequestMapping("/file/list")
	public ModelAndView list(HttpServletRequest request,
			ModelAndView mView) {
		//fileService 를 이용해서 비즈니스 로직 처리하고
		fileService.getList(request);
		//view 페이지로 forward 이동하여 응답하기
		mView.setViewName("file/list");
		return mView;
	}
	
	//파일 업로드 폼 요청 처리
	@RequestMapping("/file/private/upload_form")
	public ModelAndView uploadForm(ModelAndView mView) {
		
		mView.setViewName("file/private/upload_form");
		return mView;
	}
	
	//파일 업로드 요청 처리
	@RequestMapping(value = "/file/private/upload",
			method = RequestMethod.POST)
	public ModelAndView upload(FileDto dto, ModelAndView mView,
			HttpServletRequest request) {
		fileService.saveFile(dto, mView, request);
		mView.setViewName("file/private/upload");
		return mView;
	}
	
	//파일 다운로드 요청 처리
	//파일 다운로드를 하기 위해서는 원본 파일명, 저장된 파일명, 파일 사이즈 정보가 필요하다.
	@RequestMapping("/file/download")
	public ModelAndView download(@RequestParam int num,
			ModelAndView mView) {
		fileService.getFileData(num, mView);
		//view 페이지로 이동해서 다운로드 시켜준다.
		mView.setViewName("file/download");
		return mView;
	}
}