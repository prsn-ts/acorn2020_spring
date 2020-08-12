package test.main4;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass01 {
	public static void main(String[] args) {
		//DB에 저장될 비밀번호라고 가정
		String pwd="abcd1234";
		CharSequence pwd2 = "abcd1234";
		//비밀번호를 인코딩할 객체 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//비밀번호를 암호화한 문자열 얻어내기
		String result = encoder.encode(pwd2); // class String implements CharSequence -> String은  CharSequence를 구현함으로써 charsequence 타입을 넣어야하는 경우 String 타입을 넣으면된다.
		//결과 출력해보기
		for(int i=0; i<10; i++) {
			//비밀번호를 암호화한 문자열 얻어내기
			String result1 = encoder.encode(pwd);
			//수행할 때 마다 다른 문자열이 나온다.
			System.out.println(result1);
		}
	}
}
