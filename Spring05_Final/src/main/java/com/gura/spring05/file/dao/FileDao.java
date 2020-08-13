package com.gura.spring05.file.dao;

import java.util.List;

import com.gura.spring05.file.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int getCount(FileDto dto);
	//파일 업로드 요청 시 DB에 저장하는 추상 메소드
	public void insert(FileDto dto);
}
