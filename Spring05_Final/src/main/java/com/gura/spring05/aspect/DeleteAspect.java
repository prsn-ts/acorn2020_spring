package com.gura.spring05.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.exception.NotDeleteException;
import com.gura.spring05.file.dao.FileDao;
import com.gura.spring05.file.dto.FileDto;

@Aspect
@Component //빈으로 만들기 위함
public class DeleteAspect {
	
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CafeDao cafeDao;
	
	//남의 파일 삭제 예외처리 AOP
	@Around("execution(void com.gura.spring05.file.service.*.delete*(..))")
	public void checkFileDelete(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자값을 저장할 지역 변수
		int num = 0;
		HttpServletRequest request = null;
		
		Object[] args = joinPoint.getArgs();
		for(Object tmp:args) {
			//aop가 적용된 메소드의 인자값들을 찾는 이유는 그 인자값들을 이용해서 원하는 로직을 수행하기 위함이다.
			if(tmp instanceof Integer) { //int를 찾을 때는 Integer(참조형 데이터타입)으로 찾는다.
				num = (int)tmp;
			}
			if(tmp instanceof HttpServletRequest) { //HttpServletRequest 찾기
				request = (HttpServletRequest)tmp;
			}
		}
		//삭제할 파일의 정보를 읽어온다.
		FileDto fileDto = fileDao.getData(num);
		//세션에 저장된 아이디를 읽어온다(로그인된 아이디)
		String id=(String)request.getSession().getAttribute("id");
		if(!id.equals(fileDto.getWriter())) {
			throw new NotDeleteException("남의 파일 지우기 없기!");
		}
		
		//메소드 정상 수행하기
		joinPoint.proceed();
	}
	
	//남이 쓴글 삭제 예외처리 AOP
	@Around("execution(void com.gura.spring05.cafe.service.*.delete*(..))")
	public void checkWritingDelete(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//메소드에 전달된 인자값을 저장할 변수 선언
		int num = 0;
		HttpServletRequest request = null;
		
		//aop가 적용될 메소드의 인자값들을 Object[]인 args에 저장.
		Object[] args = joinPoint.getArgs();
		//반복문 돌면서 Object[]에 저장된 특정 데이터 타입을 찾아낸다.(aop가 적용된 메소드의 인자값들을 찾는 이유는 그 인자값들을 이용해서 원하는 로직을 수행하기 위함이다.)
		for(Object tmp:args) {
			if(tmp instanceof Integer) { //int를 찾을 때는 Integer(참조형 데이터타입)으로 찾는다.
				num = (int)tmp;
			}
			if(tmp instanceof HttpServletRequest) { //HttpServletRequest 찾기
				request = (HttpServletRequest)tmp;
			}
		}
		
		//1. 삭제할 글의 정보를 읽어온다.
		CafeDto cafeDto=cafeDao.getData(num);
		//2. 본인이 작성한 글이 아닌경우 에러 처리를한다 (예외를 발생시킨다)
		String id=(String)request.getSession().getAttribute("id");
		//만일 로그인된 아이디와 글 작성자가 다르면
		if(!id.equals(cafeDto.getWriter())) {
			throw new NotDeleteException("남의 글 지우기 없기!");
		}
		
		//메소드 정상 수행하기
		joinPoint.proceed();
	}
}
