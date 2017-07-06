package com.design.pattern.decorator;

/**
 * 具体装饰角色 软件架构师类 他具有附加的功能 能设计总个网站或系统的骨
 * 
 * @author Administrator
 *
 */
public class SoftwareArchitect extends Decorator {

	public SoftwareArchitect(Programmer programmer) {
		super(programmer);
	}

	@Override
	public void programme() {
		super.programme();
		// 附加的责任或者功能
		System.out.println("我具有架构师的技能  我能设计总个网站或系统的骨架");
	}
}
