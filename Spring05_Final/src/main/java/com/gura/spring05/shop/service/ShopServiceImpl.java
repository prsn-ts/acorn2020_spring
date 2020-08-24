package com.gura.spring05.shop.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.shop.dao.OrderDao;
import com.gura.spring05.shop.dao.ShopDao;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void getList(ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}
	/*
	 *  - Spring 트랜잭션 설정 방법
	 *  1. pom.xml 에 spring-tx dependency 추가
	 *  2. servlet-context.xml 에 transaction 설정 추가
	 *  3. 트랜잭션을 관리할 서비스의 메소드에 @Transactional 어노테이션 붙이기
	 */
	/*
	 *  만약 프로그래머가 로직 수행중에 임의로 exception을 발생시켜서 수행중이던 로직을 모두 취소하고 원래대로 되돌리고 싶다면
	 *  DataAccessException을 상속받는 커스텀 클래스를 만들어서 throw를 시켜주면 예외 처리가 발생했을 때 트랜잭션이 진행중이던 로직들이 모두 취소가 된다.
	 *  
	 *  - 프로그래머의 의도하에서 트랜잭션에 영향을 주는 Exception을 발생 시키는 방법
	 *  
	 *  DataAccessException 클래스를 상속받은 클래스를 정의하고
	 *  예) class MyException extends DataAccessException{ }
	 *  	throw new MyException("예외 메세지");
	 *  
	 *  예외를 발생시킬 조건이라면 위와 같이 예외를 발생시켜서
	 *  트랜잭션이 관리 되도록 한다.
	 */
	@Transactional //어느하나 빠짐없이 로직 수행을 해야하는데 exception이 발생해서 모두 취소해야한다면 트랜잭션 어노테이션을 붙인다.
	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		//1. 구입할 상품의 번호를 읽어온다.
		
		//2. 상품의 가격을 얻어온다.
		
		//3,4,5,6번의 작업을 트랜잭션으로 묶어야한다.(도중에 실패하면 다시 처음부터 시작해야한다)
		//3. 상품의 가격만큼 계좌 잔액을 줄인다.
		
		//4. 가격의 10%를 포인트로 적립한다.
		
		//5. 재고의 개수를 1 줄인다.
		
		//6. 주문 테이블(배송) 에 정보를 추가한다.
		
	}

}
