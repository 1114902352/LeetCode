package com.design.pattern.decorator;

/**
 * 具体装饰角色 黑客类 他具有附加的功能 他能入侵别人的电脑
 * 
 * @author Administrator
 *
 */
public class Hacker extends Decorator {

	public Hacker(Programmer programmer) {
		super(programmer);
	}

	@Override
	public void programme() {
		super.programme();
		// 附加的责任或者功能
		System.out.println("我具有黑客的技能   我能入侵别人的电脑");
	}

}
