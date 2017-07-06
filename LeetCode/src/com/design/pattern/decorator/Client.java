package com.design.pattern.decorator;

public class Client {

	public static void main(String[] args) {
		// 创建构件对象 汤高 ->他是一个具体的程序员
		// 但是现在他只有编程能力 那怎么行
		// 必须赋予他更大的能力 不然怎么赚大钱!
		// 所以 上帝给他装饰了一番 瞬间提神了他的B格
		Programmer programmer = new Tom();

		// 装饰类登场 屌丝 我来装饰你 让你具有更大的能力
		Decorator hacker = new Hacker(programmer);
		// 这下汤高这个屌丝程序员就具有黑客的技能包了
		// 这下就没有谁敢叫他屌丝了吧 哈哈 不然分分钟让你电脑崩溃
		System.out.println("第一次装饰");
		hacker.programme();

		// 程序员还不满足 他还要更多的技能 因为他要逆袭
		// 所以上帝再给他装饰了一下
		// 在他具有黑客技能的基础上另外赋予了他架构师的功能
		System.out.println("--------------第二次装饰");
		Decorator achitect = new SoftwareArchitect(hacker);

		achitect.programme();
		// 也可以一步装饰两个技能 因为他们有共同的父类抽象构件接口 Programmer
		System.out.println("------------一步装饰两个技能");
		Decorator achitect1 = new SoftwareArchitect(new Hacker(new Tom()));
		achitect1.programme();
	}

}
