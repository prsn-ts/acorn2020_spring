package test.main5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.WritingUtil;

public class MainClass01 {
	public static void main(String[] args) {
		//init.xml 문서를 로딩해서 bean 으로 관리할 객체들을 관리한다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("test/main5/init.xml"); //init.xml이 로딩될 때 component scan에 의해 @component 붙은 곳에 객체가 생성된다.
		//관리되는 객체중에서 WritingUtil type 객체의 참조값을 얻어낸다.
		WritingUtil util = context.getBean(WritingUtil.class);
		//객체의 메소드 호출하기
		util.write1();
		util.write2();
		util.write3();
	}
}