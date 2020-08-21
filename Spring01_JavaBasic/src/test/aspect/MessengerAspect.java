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
	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) 
				throws Throwable {
		//aop 가 적용된 메소드 수행하기 이전에 해야할 작업
		
		//aop 가 적용된 메소드 수행하기(joinPoint.proceed(); 이 구문을 실행하지 않을 경우 aop가 적용된 메소드가 실행되지 않는 다는 특징이 있다)
		//이 점을 이용하면 aop가 적용된 메소드가 수행했는 지 안했는 지 검사하는 용도로 쓸 수도 있다.
		joinPoint.proceed(); 
		
		//aop 가 적용된 메소드 수행된 이후에 해야할 작업
	}
}
