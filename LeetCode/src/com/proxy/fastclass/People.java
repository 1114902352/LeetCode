package com.proxy.fastclass;

public class People {

	private String name;
	private int age;
	private String sex;

	public People(){}
	
	public People(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString(){
		String temp = this.name+" "+this.age+" "+this.sex;
		return temp;
	}
}
