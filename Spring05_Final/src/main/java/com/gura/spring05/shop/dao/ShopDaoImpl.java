package com.gura.spring05.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.shop.dto.ShopDto;

@Repository
public class ShopDaoImpl implements ShopDao{
	@Autowired
	private SqlSession session;
	/* Autowired 한 것은 setter 메소드가 생성이되서 실행 시점에 session 필드에 SqlSession 객체의 참조값이 저장된다.
	public void setSession(SqlSession session) {
		this.session = session;
	}
	*/
	//상품의 정보를 가져오는 메소드
	@Override
	public List<ShopDto> getList() {
		return session.selectList("shop.getList");
	}
	//재고의 개수를 1 줄이기
	@Override
	public void minusCount(int num) {
		session.update("shop.minusCount", num);
	}
	//계좌 잔고 줄이기 
	@Override
	public void minusMoney(ShopDto dto) {
		session.update("shop.minusMoney", dto);
	}
	//상품 구입 가격의 10% 를 포인트로 적립하는 메소드
	@Override
	public void plusPoint(ShopDto dto) {
		session.update("shop.plusPoint", dto);
	}
	//상품 번호에 해당하는 상품의 가격을 리턴하는 메소드
	@Override
	public int getPrice(int num) {
		return session.selectOne("shop.getPrice", num);
	}

}
