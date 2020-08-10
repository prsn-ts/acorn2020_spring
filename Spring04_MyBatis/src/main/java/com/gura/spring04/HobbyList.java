package com.gura.spring04;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="hobby-list") //부모 타입의 요소명이 hobby-list 이다.
public class HobbyList {
	private List<String> hobby; //필드명인 hobby는 hobby-list 요소 안에 있는 자식 타입의 요소명이 된다.
	
	@XmlElement
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public List<String> getHobby() {
		return hobby;
	}
}
