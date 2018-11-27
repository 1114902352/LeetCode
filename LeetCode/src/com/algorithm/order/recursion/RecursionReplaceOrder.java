package com.algorithm.order.recursion;

/**
 * 使用递归算法对int数据进行置换顺序，如输入 12345  输出54321
 */
public class RecursionReplaceOrder {

	/**
	 * 递归算法，要注意参数穿什么，返回值返回什么，每层方法里面需要做些什么事，何时结束
	 * 原理:
	 * 每层方法内取出最后一位值，然后把剩余值传给下一层，直到剩余值为个位时返回给上层，上层将自己取到的最后一位值与下层拼接并返回
	 * @param number 每层取出最后一位的剩余值
	 * @return 本层最后一位与下层返回值的拼接，最后一层直接返回当前值.
	 */
	public static int permutation(int number) {
		if (number < 10) {
			return number;
		}
		int num = number % 10;
		String result = num+""+permutation(number / 10);
		return Integer.parseInt(result);
	}

	public static void main(String[] args) {
		int result = RecursionReplaceOrder.permutation(12345);
		System.out.println(result);
	}
}
