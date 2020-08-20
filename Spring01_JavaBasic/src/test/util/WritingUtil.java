package test.util;

import org.springframework.stereotype.Component;

//component scan 을 했을 때 bean 이 될 수 있도록 @Component 설정.
@Component
public class WritingUtil {
	public void write1() {
		System.out.println("편지를 써요");
	}
	public void write2() {
		System.out.println("보고서를 써요");
	}
	public void write3() {
		System.out.println("일기를 써요");
	}
}
