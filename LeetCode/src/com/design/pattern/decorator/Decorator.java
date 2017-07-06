package com.design.pattern.decorator;

/**
 * 装饰角色
 * 
 * @author Administrator
 *
 */
public class Decorator implements Programmer {
	private Programmer programmer;

	public Decorator(Programmer programmer) {
		this.programmer = programmer;
	}

	@Override
	public void programme() {
		programmer.programme();
		// 附加的责任或者功能
	}
}