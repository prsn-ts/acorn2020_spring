package com.gura.spring05.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dao.UsersDao;
import com.gura.spring05.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao dao;

	@Override
	public Map<String, Object> isExistId(String inputId) {
		boolean isExist = dao.isExist(inputId);
		//아이디가 존재하는 지 여부를 Map 에 담아서 리턴해준다.
		Map<String, Object> map = new HashMap<>();
		map.put("isExist", isExist);
		return map;
	}

	@Override
	public void addUser(UsersDto dto) {
		//비밀번호를 암호화할 BcryptPasswordEncoder 객체 생성.
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		//dto에 있는 입력된 비밀번호 암호화해서 다시 dto의 pwd 필드에 넣는다.
		dto.setPwd(pe.encode(dto.getPwd()));
		// dao 객체를 이용해서 새로운 사용자 정보를 DB에 저장하기
		dao.insert(dto);
	}

	@Override
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session) {
		//입력한 정보가 유효한 정보인지 여부를 저장할 지역변수
		boolean isValid = false;
		//로그인 폼에 입력한 아이디를 이용해서 DB에서 select 해본다.
		//존재하지 않는 아이디면 null 이 리턴된다.
		UsersDto resultDto = dao.getData(dto.getId());
		if(resultDto != null) {//아이디는 존재하는 경우(아이디는 일치)
			//DB 에 저장된 암호화된 비밀번호
			String encodedPwd = resultDto.getPwd();
			//로그인 폼에 입력한 비밀번호
			String inputPwd = dto.getPwd();
			//BCrypt 클래스의 static 메소드를 이용해서 일치 여부를 얻어낸다.
			isValid = BCrypt.checkpw(inputPwd, encodedPwd);
		}
			
		/* DB에 비밀번호 암호화 하기전의 코드
		//dao 객체를 이용해서 id, pwd 가 유효한 정보인지 여부를 얻어낸다.
		boolean isValid = dao.isValid(dto);
		*/
		
		if(isValid) {//만일 유효한 정보이면
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
			//view 페이지에서 사용할 정보(view 페이지에서 성공유무에 따라 다른 결과를 보여주기 위한 정보) 
			mView.addObject("isSuccess", true);
		}else {//아니면
			mView.addObject("isSuccess", false);
		}
	}
	/*
	//나의 info.do 요청 관련 추상 메소드
	@Override
	public void getData(UsersDto dto, String id, ModelAndView mView) {
		//dao에 id에 해당하는 회원의 정보를 가져와서 dto에 저장한다.
		dto = dao.getData(id);
		//dto에 저장된 회원을 ModelAndView 객체에 키값으로 저장한다.
		mView.addObject("dto", dto);
	}
	*/
	//Teacher info.do 요청 관련 추상 메소드
	@Override
	public void getInfo(HttpSession session, ModelAndView mView) {
		//로그인된 아이디를 session 객체를 이용해서 얻어온다.
		String id=(String)session.getAttribute("id");
		//dao 를 이용해서 사용자 정보를 얻어와서
		UsersDto dto = dao.getData(id);
		//mView 객체에 담아준다.
		mView.addObject("dto", dto);
	}

	@Override
	public void deleteUser(HttpSession session) {
		//세션에 저장된 아이디를 읽어와서
		String id = (String)session.getAttribute("id");
		//삭제
		dao.delete(id);
		//로그 아웃 처리
		session.invalidate();
	}

	@Override
	public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		//원본 파일명
		String orgFileName = mFile.getOriginalFilename();
		
		//임시 폴더에 있는 파일을 upload 폴더에 옮겨야한다.
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
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
			//(서버의 파일 시스템 상에서의 경로+File.separator(filePath)와 저장된 파일 이름(saveFileName)의 정보를 가진 파일 객체를 생성한 후에 transferTo 함수의 인자로 던져주면 내부적으로 임시 폴더에 있던 파일을 upload 폴더에 옮겨준다(저장해준다))
			mFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Map 에 업로드된 이미지 파일의 경로를 담아서 리턴한다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageSrc", "/upload/"+saveFileName);
		
		return map;
	}

	@Override
	public void updateUser(HttpSession session, UsersDto dto) {
		//로그인된 아이디를 읽어와서
		String id = (String)session.getAttribute("id");
		//UsersDto 에 담고
		dto.setId(id);
		//프로필 없이 개인정보 수정했을 때 들어오는 값 타입 확인용.
		System.out.println("dto:"+dto.getProfile().getClass().getName());
		/*
		//만일 프로필 이미지를 수정하지 않았다면 hidden type의 value가 "" 빈 문자열이 넣어져있으므로 직접 null을 dto에 넣기.
		if(dto.getProfile().equals("")) {
			dto.setProfile(null);
		}
		*/
		//dao 를 이용해서 수정반영하기
		dao.update(dto);
	}

	@Override
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView) {
		String id = (String)session.getAttribute("id");
		//세션 영역에 저장된 아이디를 dto 에 넣어준다.(dao.updatePwd() -> updatePwd() 함수 관련 mapper에서 사용함)
		dto.setId(id);
		//작업 성공여부
		boolean isSuccess = false;
		//1. 기존 비밀번호와 암호화된 비밀번호가 일치하는지 확인
		UsersDto resultDto = dao.getData(dto.getId()); //이미 로그인한 경우이기 때문에 null 일 가능성은 없다.
		if(resultDto != null) {//아이디는 존재하는 경우(아이디는 일치)
			//DB 에 저장된 암호화된 비밀번호
			String encodedPwd = resultDto.getPwd();
			//로그인 폼에 입력한 비밀번호
			String inputPwd = dto.getPwd();
			//BCrypt 클래스의 static 메소드를 이용해서 일치 여부를 얻어낸다.
			boolean isValid = BCrypt.checkpw(inputPwd, encodedPwd);
			//2. 만일 일치한다면 새로운 비밀번호를 암호화 해서 저장한다.
			if(isValid) {
				//새로운 비밀번호를 암호화 한다.
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String encodedNewPwd = encoder.encode(dto.getNewPwd());
				//암호화된 새 비밀번호를 dto 에 다시 넣어준다.
				dto.setNewPwd(encodedNewPwd);
				//dao 를 이용해서 DB 에 반영한다.
				dao.updatePwd(dto);
				//성공
				isSuccess = true;
			}
		}
		//mView 객체에 성공 여부를 담는다.
		mView.addObject("isSuccess", isSuccess);
		
		/* DB에 비밀번호 암호화 하기 전의 코드
		dto.setId(id);
		//dao 를 이용해서 비밀번호를 수정한다.(실패 가능성 있음)
		boolean isSuccess = dao.updatePwd(dto);
		//mView 객체에 성공 여부를 담는다.
		mView.addObject("isSuccess", isSuccess);
		*/
	}
}
