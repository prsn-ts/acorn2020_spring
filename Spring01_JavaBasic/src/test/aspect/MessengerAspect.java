package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {
	// return type 은 void 이고 send로 시작하는 모든 메소드가 point cut(aop가 적용되는 그 위치 혹은 메소드를 말함)이다.
	// (..) -> 이 의미는 인자값이 아예 없을 수도 있고 1개 있을 수도 있고 2개 있을 수도 있고 여러개 있을 수도 있다는 의미를 내포함.
	@Around("execution(void send*(..))") //@Before, @After 어노테이션를 합친 것이 @Around 이고 한 가지 다른 점은 ProceedingJoinPoint 객체가 있다는 것.
	public void checkGreeting(ProceedingJoinPoint joinPoint) //ProceedingJoinPoint 객체는 @Around 어노테이션이 붙은 곳에 사용할 수 있고 이 객체를 이용해 aop가 적용된 메소드를 실행할 지 말 지 까지도 결정할 수 있게된다.
			throws Throwable { 
		//aop 가 적용된 메소드 수행하기 이전에 해야할 작업
		//메소드에 전달된 인자들 목록을 얻어내기
		Object[] args = joinPoint.getArgs(); //aop가 적용된 메소드의 인자들의 값들(여러개)을 배열로 담기위한 getArgs() 메소드
		for(Object tmp:args) { //aop가 적용된 여러 메소드의 인자값은 어떤 일련의 순서대로 args Object[]배열에 저장되리란 보장이 없기 때문에 원하는 데이터 type을 찾아서 작업을 진행해야한다.
			//만일 인자가 String type 이면
			if(tmp instanceof String) { //tmp에 들어있는 데이터 타입이 String일 경우 "tmp instanceof String" 이 구문의 결과값이 true로 바뀐다.
				//원래 type 으로 casting
				String msg = (String)tmp;
				System.out.println("aspect 에서 읽어낸 내용:"+msg);
				if(msg.contains("바보")) {
					System.out.println("바보는 금지된 단어 입니다.");
					return; //메소드를 여기서 끝내기 (proceed() 가 호출이 안된다 호출이 안되면 aop가 적용된 메소드도 호출이 되지 않게 된다.)
				}
			}
		}
		//aop 가 적용된 메소드 수행하기(joinPoint.proceed(); 이 구문을 실행하지 않을 경우 aop가 적용된 메소드가 실행되지 않는 다는 특징이 있다)
		//이 점을 이용하면 aop가 적용된 메소드가 수행했는 지 안했는 지 검사하는 용도로 쓸 수도 있다.
		joinPoint.proceed(); //이 메소드를 호출하지 않을 경우 aop를 적용한 메소드가 호출은 되지만 메소드 안에 내용은 실행이 안되고 리턴이 된다.
		
		//aop 가 적용된 메소드 수행된 이후에 해야할 작업
	}
	
	@Around("execution(String get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//aop 가 적용된 메소드가 리턴하는 데이터 얻어내기
		Object obj = joinPoint.proceed(); //joinPoint.proceed()부분이 실행되고나면 aop가 적용된 메소드가 실행이되고 그 메소드의 리턴값인 "공부하자!" 의 내용이 obj 변수에 들어간다.
		
		//aop 가 적용된 메소드가 리턴하는 데이터
		return "놀자  놀자~"; //aop가 적용된 메소드가 원래 리턴하는 값은 "공부하자!" 인데 여기서 리턴값을 "놀자  놀자~"로 바꾸면 aop가 적용된 메소드의 리턴값이 "놀자  놀자~"로 바뀐다. 
	}
}
