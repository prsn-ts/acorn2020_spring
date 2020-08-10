package com.gura.spring04.todo.dto;

public class TodoDto {
	//필드
	private int num;
	private String work;
	private String regdate;
	
	//기본 생성자
	public TodoDto() {}

	public TodoDto(int num, String work, String regdate) {
		super();
		this.num = num;
		this.work = work;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
