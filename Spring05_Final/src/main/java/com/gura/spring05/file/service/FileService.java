package com.gura.spring05.file.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.file.dto.FileDto;

public interface FileService {
	public void getList(HttpServletRequest request);
	//파일 업로드 요청 처리 관련 메소드
	public void saveFile(FileDto dto, ModelAndView mView,
			HttpServletRequest request);
	//파일 다운로드 요청 처리 관련 메소드
	public void getFileData(int num, ModelAndView mView);
	//파일 삭제 요청 처리 관련 메소드
	public void deleteFile(int num, HttpServletRequest request);
	
}
