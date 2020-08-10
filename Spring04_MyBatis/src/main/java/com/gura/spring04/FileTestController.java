package com.gura.spring04;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileTestController {
	
	// 스프링에서 파일 처리하는 방법
	//1. 파일을 어딘가의 임시 폴더에 저장해놓는다.
	//2. 업로드된 파일의 정보가 MultipartFile myFile의 객체에 들어온다.
	//3. myFile 객체에 있는 정보를 가지고 임시 폴더에 저장된 파일을 upload 폴더(저장된 파일을 사용할 폴더)에 복사하는 작업을 해야한다.
	@RequestMapping("/upload")
	public String upload(@RequestParam MultipartFile myFile, // <input type="file" name="myFile" /> 하나만 전송되는 경우
			HttpServletRequest request) { //필요시 HttpServletRequest를 선언하면 Controller가 객체의 참조값을 넣어준다.
		//원본 파일명과 파일의 크기를 알아낸다.
		//원본 파일명
		String orgFileName = myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		
		//임시 폴더에 있는 파일을 upload 폴더에 옮겨야한다.
		// webapp/upload 폴더 까지의 실제 경로
		String realPath = request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로
		String filePath = realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName = 
				System.currentTimeMillis()+orgFileName;
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		return "upload";
	}
}
