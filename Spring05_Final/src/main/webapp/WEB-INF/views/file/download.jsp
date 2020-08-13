<%@page import="com.gura.spring05.file.dto.FileDto"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request 영역에 있는 dto 객체의 참조값을 가져온다.
	FileDto dto = (FileDto)request.getAttribute("dto");	

	//3. 해당 파일을 실제로 다운로드 시켜준다(응답) - 페이지를 이동하지 않고 다운로드를 시켜줄 것, html 응답안함(다운로드만 할 수 있도록)
	//다운로드 시켜주기 ...(원본 파일명, 저장된 파일명, 파일 사이즈를 알아야 다운로드 가능)
	String orgFileName=dto.getOrgFileName(); //원본 파일명
	String saveFileName=dto.getSaveFileName(); //저장된 파일명
	
	//다운로드 시켜줄 파일의 실제 경로 구성하기
	String path=application.getRealPath("/upload")
			+File.separator+saveFileName; //DB에 저장된 겹치지 않는 파일명에 해당하는 파일을 불러오기/ 다운로드된 파일을 보여주려고 한다면 원본 파일명으로 보여주면 된다.
	//실제 DB에 다운로드되는 경로 보기
	System.out.println(path);
	//다운로드 할 파일을 읽어올 스트림 객체 생성하기
	FileInputStream fis=new FileInputStream(path);
	
	//다운로드 시켜주는 작업을 한다. (실제 파일 데이터와 원본파일명을 보내줘야한다.)
	//다운로드 시켜주는 작업을 한다. 
	String encodedName=null;
	System.out.println(request.getHeader("User-Agent"));
	//한글 파일명 세부처리 
	if(request.getHeader("User-Agent").contains("Firefox")){
		//벤더사가 파이어 폭스인경우 
		encodedName=new String
			(orgFileName.getBytes("utf-8"),"ISO-8859-1");
	}else{ //그외 다른 벤더사 
		encodedName=URLEncoder.encode(orgFileName, "utf-8");
		//파일명에 공백이있는 경우 처리 
		encodedName=encodedName.replaceAll("\\+"," ");
	}
	
	//응답 헤더 정보 설정
	response.setHeader("Content-Disposition","attachment;filename="+encodedName);
	response.setHeader("Content-Transfer-Encoding", "binary");
	
	//다운로드할 파일의 크기 읽어와서 다운로드할 파일의 크기 설정
	response.setContentLengthLong(dto.getFileSize()); //웹브라우저에게 다운시킬 파일 크기를 알려줌(그래야 다운로드 가능)
	
	//클라이언트에게 출력할수 있는 스트림 객체 얻어오기
	BufferedOutputStream bos=
		new BufferedOutputStream(response.getOutputStream());
	//한번에 최대 1M byte 씩 읽어올수 있는 버퍼
	byte[] buffer=new byte[1024*1000];
	int readedByte=0;
	//반복문 돌면서 출력해주기
	while(true){
		//byte[] 객체를 이용해서 파일에서 byte 알갱이 읽어오기
		readedByte = fis.read(buffer);
		if(readedByte == -1)break; //더이상 읽을 데이터가 없다면 반복문 빠져 나오기
		//읽은 만큼 출력하기
		bos.write(buffer, 0, readedByte);
		bos.flush(); //출력
	}
	//스트림 닫아주기
	bos.close();
	fis.close();
%>