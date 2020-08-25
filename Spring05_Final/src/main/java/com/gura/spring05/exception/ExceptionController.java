package com.gura.spring05.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//예외 컨트롤러를 bean 으로 만들기 위한 어노테이션
@ControllerAdvice //Exception Controller가 bean이 되기 위해서 필요한 @ControllerAdvice 어노테이션을 붙이고 Component-scan에 의해 bean 이 된다.
public class ExceptionController {
	
	//spring framework 가 동작하는 중에 NotDeleteException type 의
	//예외가 발생하면 호출되는 메소드
	@ExceptionHandler(NotDeleteException.class)
	public ModelAndView notDelete(NotDeleteException nde) {
		//해당 오류가 발생했을 때 원하는 작업을 한 후
		
		//view page 로 forward 이동해서 예외 정보를 응답한다.
		ModelAndView mView = new ModelAndView();
		//exception 이라는 키값으로 예외 객체를 담고
		mView.addObject("exception", nde);
		// /WEB-INF/views/error/info.jsp 페이지로 forward 이동
		mView.setViewName("error/info");
		return mView;
	}
	/*	@Repository 어노테이션이 붙은
	 *  Dao 에서 DB 관련 작업을 하다가 예외가 발생하면 실행순서가 여기로 들어온다.
	 */
	
	/*
	 *  NoDeliveryException 클래스는 DataAccessException를 상속받아서 DataAccessException 타입이기도 하나
	 *  이렇게 좀더 세부적인 처리를 위해서 따로 컨트롤러에 특정 클래스를 지정해서 예외 처리 요청을 설정할 수 있다.
	 *  ※ 주의! 
	 *  -> NoDeliveryException의 클래스의 세부적인 예외 처리 요청은 DataAccessException 예외 처리 요청보다
	 *     위(상단)에 있어야한다.
	 */
	@ExceptionHandler(NoDeliveryException.class)
	public ModelAndView noDelivery(NoDeliveryException nde) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception", nde);
		mView.setViewName("error/no_delivery");
		return mView;
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception", dae);
		mView.setViewName("error/data_access");
		return mView;
	}
}
