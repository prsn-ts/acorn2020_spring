package com.gura.spring05.file.dao;

import java.util.List;

import com.gura.spring05.file.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int getCount(FileDto dto);
	//파일 업로드 요청 시 DB에 저장하는 메소드
	public void insert(FileDto dto);
	//다운로드할 파일의 정보를 가져오기위한 메소드
	public FileDto getData(int num);
	//파일 삭제 처리하는 메소드
	public void delete(int num);
}
