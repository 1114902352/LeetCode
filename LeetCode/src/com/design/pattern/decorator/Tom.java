package com.design.pattern.decorator;

/**
 * 具体构件角色 汤高是一个具体的程序员 那么他就具有编程能力
 * 
 * @author Administrator
 *
 */
public class Tom implements Programmer {

	@Override
	public void programme() {
		System.out.println("我是一个程序员, 我能编程");
	}

}
