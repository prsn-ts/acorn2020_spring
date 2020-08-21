package test.util;

import org.springframework.stereotype.Component;

@Component //@Component 어노테이션으로 Component-scan에 의해 bean 객체가 생성된다.
public class Messenger {
	public void sendGreeting(String msg) {
		System.out.println("전달된 오늘의 인사 : "+msg);
	}
	public String getMessage() {
		System.out.println("getMessage() 메소드가 수행 됩니다.");
		return "공부하자!";
	}
}
