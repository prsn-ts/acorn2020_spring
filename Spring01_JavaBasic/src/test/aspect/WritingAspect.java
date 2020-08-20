package test.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 *  Aspect : 공통관심사 (횡단 관심사)
 *  
 *  - 핵심 비즈니스 로직과는 상관없는 공통관심사를 따로 작성한다.
 *  - 횡단 관심사가 들어갈 메소드가 호출되기 바로 직전(@Before) or 호출되는 도중에 or 호출되고 난 이후에 적용시킬 수 있다.
 */

/*
 * 	-Aspectj Expression
 * 
 * 	1. execution(* *(..)) => 접근 가능한 모든 메소드가  -> (*) -> 인자가 반드시 1개여야함, (*,*) -> 인자가 반드시 2개여야함
 * 	   point cut(aop가 사용되는 바로 그 지점을 말함)
 * 	2. execution(* test.service.*.*(..))
 * 		=> test.service 패키지의 모든 메소드 point cut
 *  2-1. execution(* test.service.CafeService.*(..)) 
 * 		=> test.service 패키지의 CafeService 클래스(객체)의 모든 메소드 point cut
 * 	3. execution(void insert*(..))
 * 		=>리턴 type 은 void 이고 메소드명이 
 * 		insert 로 시작하는 모든 메소드가 point cut
 * 	4. execution(* delete*(*))
 * 		=> 메소드 명이 delete 로 시작하고 인자로 1개 전달받는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 * 	5. execution(* delete*(*,*))
 * 		=> 메소드 명이 delete 로 시작하고 인자로 2개 전달받는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 *  6. execution(String update*(Integer,*))
 *  	=> 메소드 명이 update로 시작하고 리턴 type은 String
 *  	메소드의 첫번째 인자는 Integer type, 두번째 인자는 아무 type 다되는
 *  	메소드가 point cut (aop가 적용되는 위치)
 */

@Aspect //aspect 역할을 할 수 있도록 필요한 어노테이션
@Component //component scan 을 통해서 bean이 되기 위한 어노테이션
public class WritingAspect {
	//메소드가 수행되기 바로 이전에 적용될 수 있도록 설정하는 어노테이션
	//메소드가 호출되기 직전에 수행되는 @Before는 Before가 적용되는 메소드의 인자값을 활용하여 검사할 수 없기 때문에 활용도가 낮다.
	@Before("execution(void write*())") //수많은 bean 객체들 중에서 어떤 모양의 메소드에 적용할 지를 정하기위해 ("execution(void write*())") 이렇게 괄호안에다가 적는다.
	public void prepare() {
		System.out.println("pen 을 준비해요");
	}
	//메소드가 수행되고 난 이후에 적용될 수 있도록 설정하는 어노테이션
	//메소드가 수행되고 난 이후에 수행되는 @After는 After가 적용되는 메소드의 리턴값을 확인하는 작업 등의 검사를 할 수 없기 때문에 활용도가 낮다.
	@After("execution(void write*())")
	public void end() {
		System.out.println("다 사용한 pen 을 마무리 해요.");
	}
}
