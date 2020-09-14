package com.gura.spring05;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.spring05.users.dao.UsersDao;
import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class TestController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private UsersDao usersDao;
	
	@RequestMapping("/api/get_info")
	@ResponseBody
	public JSONPObject jsonp(@RequestParam(defaultValue="callback") String callback) {
		//클라이언트에게 응답할 데이터를 Map에 담는다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", 1);
		map.put("name", "김구라");
		//JSONPObject 객체를 생성해서 콜백 함수명과 응답할 데이터를 담고
		JSONPObject jp = new JSONPObject(callback, map);
		//JSONPObject 를 리턴해준다.
		return jp;
	}
	//로그인 요청 처리(Teacher.ver)
	@RequestMapping("/api/jsonp_login")
	@ResponseBody
	public JSONPObject jsonpLogin(String callback, UsersDto dto) {
		//유효한 정보인지 여부를 얻어온다. 
		boolean isValid=usersService.jsonpLogin(dto);
		//유효한지 여부를 Map 에 담고
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isValid", isValid);
		//JSONPObject 객체에 담아서 
		JSONPObject jp=new JSONPObject(callback, map);
		return jp; //리턴해준다. 
	}
	
	//로그인 요청 처리
	@RequestMapping("/api/login_process")
	@ResponseBody
	public JSONPObject loginProcess(@RequestParam(defaultValue="callback") String callback,
			UsersDto dto, HttpSession session ) {
		//입력한 정보가 유효한 정보인지 여부를 저장할 지역변수
		boolean isValid = false;
		//로그인 폼에 입력한 아이디를 이용해서 DB에서 select 해본다.
		//존재하지 않는 아이디면 null 이 리턴된다.
		UsersDto resultDto = usersDao.getData(dto.getId());
		if(resultDto != null) {//아이디는 존재하는 경우(아이디는 일치)
			//DB 에 저장된 암호화된 비밀번호
			String encodedPwd = resultDto.getPwd();
			//로그인 폼에 입력한 비밀번호
			String inputPwd = dto.getPwd();
			//BCrypt 클래스의 static 메소드를 이용해서 일치 여부를 얻어낸다.
			isValid = BCrypt.checkpw(inputPwd, encodedPwd);
		}

		//클라이언트에게 응답할 데이터를 Map에 담는다.
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(isValid) {//만일 유효한 정보이면
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
			//view 페이지에서 사용할 정보(view 페이지에서 성공유무에 따라 다른 결과를 보여주기 위한 정보) 
			map.put("isSuccess", true);
			map.put("id", session.getAttribute("id"));
		}else {//아니면
			map.put("isSuccess", false);
		}
		
		//JSONPObject 객체를 생성해서 콜백 함수명과 응답할 데이터를 담고
		JSONPObject jp = new JSONPObject(callback, map);
		//JSONPObject 를 리턴해준다.
		return jp;
	}
	
	//ajax로 로그아웃 요청 처리
	@RequestMapping("/api/logout.do")
	@ResponseBody
	public JSONPObject ajaxLogout(@RequestParam(defaultValue="callback") String callback, HttpSession session){
		//로그아웃 처리를 한다.
		session.invalidate();
		//map 객체를 만들고
		Map<String, Object> map = new HashMap<String, Object>();
		//로그아웃 성공 유무를 담고
		map.put("isSuccess", true);
		
		//JSONPObject 객체를 생성해서 콜백 함수명과 응답할 데이터를 담고
		JSONPObject jp = new JSONPObject(callback, map);
		//JSONPObject 를 리턴해준다.
		return jp;
	}
}
