package com.gura.spring05.shop.service;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.exception.NoDeliveryException;
import com.gura.spring05.shop.dao.OrderDao;
import com.gura.spring05.shop.dao.ShopDao;
import com.gura.spring05.shop.dto.OrderDto;
import com.gura.spring05.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void getList(ModelAndView mView) {
		//상품 목록
		List<ShopDto> list = shopDao.getList();
		//ModelAndView 객체에 list 라는 키값으로 담는다.
		mView.addObject("list", list);
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
	@Transactional //어느하나 빠짐없이 성공적으로 로직 수행을 해야하는데 중간에 의도치 않은 exception이 발생해서 모두 취소해야한다면 트랜잭션 어노테이션을 붙인다.
	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		//구입자의 아이디(누가)
		String id = (String)request.getSession().getAttribute("id");
		//1. 파라미터로 전달되는 구입할 상품 번호(어떤 상품을 구입할 것인지)
		int num = Integer.parseInt(request.getParameter("num"));
		//2. 상품의 가격을 얻어온다.
		int price = shopDao.getPrice(num);
		//3,4,5,6번의 작업을 트랜잭션으로 묶어야한다.(도중에 실패하면 다시 처음부터 시작해야한다)
		//3. 상품의 가격만큼 계좌 잔액을 줄인다.
		ShopDto dto = new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		shopDao.minusMoney(dto);
		//4. 가격의 10%를 포인트로 적립한다.
		shopDao.plusPoint(dto);
		//5. 재고의 개수를 1 줄인다.
		shopDao.minusCount(num);
		//6. 주문 테이블(배송) 에 정보를 추가한다.
		OrderDto dto2 = new OrderDto();
		dto2.setId(id); //누가
		dto2.setCode(num); //어떤 상품을
		dto2.setAddr("강남구 삼원빌딩 5층"); //어디로 배송할 지
		
		//테스트로 예외를 발생 시켜 보기
		Random ran = new Random();
		int ranNum=ran.nextInt(3); //0 or 1 or 2 랜덤한 정수 발생 시키기
		if(ranNum==0) {//만일 0이 나오면
			throw new NoDeliveryException("배송 기사가 아파서 배송을 못해요..ㅜ");
		}
		
		orderDao.addOrder(dto2);
	}

}
