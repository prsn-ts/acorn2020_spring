package test.auto;

public class Car{
	//필드
	private Engine engine;
	//생성자
	//생성자의 인자로 Engine 객체를 전달해야 Car 객체가 생성이됨.
	//또한 기본 생성자는 정의하지 않았으므로 기본 생성자로는 객체 생성 불가.
	public Car(Engine engine) {
		this.engine = engine;
	}
	public void drive() {
		if(engine==null) {
			System.out.println("Engine 객체가 없어서 달릴 수가 없어요");
		}else {
			System.out.println("달려요!");
		}
	}
}
