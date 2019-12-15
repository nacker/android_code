package com.devlei.demo.bean;

public class Student {
	
	private String id;
	private String name;
	private String phone;
	
	public Student(String id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	
}
