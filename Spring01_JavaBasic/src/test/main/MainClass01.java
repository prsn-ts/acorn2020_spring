package test.main;

import test.mypac.Weapon;
import test.mypac.WeaponImpl;

public class MainClass01 {
	public static void main(String[] args) {
		//Application의 목적이 단순히 무언가를 공격하는게 목적이라면
		
		//1. 목적을 달성하기 위해 필요한 핵심 의존 객체를 직접 생성해서
		Weapon w = new WeaponImpl();
		//2. 메소드를 호출해서 목적을 달성한다.
		w.attack();
		
		//Application의 목적이 무언가를 공격하는것의 목적 달성뿐만 아니라 유지보수도 쉽게하려면?(MainClass02 참고)
		//각 클래스들간에 의존 관계를 느슨하게 해야한다. 그러기 위해서는?
		//1. 인터페이스 타입을 적극활용하기
		//2. 필요한 핵심 객체를 직접 new 하지 않는다.
	}
}
