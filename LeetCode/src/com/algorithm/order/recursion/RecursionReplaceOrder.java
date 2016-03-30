package com.algorithm.order.recursion;

/**
 * ʹ�õݹ��㷨��int���ݽ����û�˳�������� 12345  ���54321
 */
public class RecursionReplaceOrder {

	/**
	 * �ݹ��㷨��Ҫע�������ʲô������ֵ����ʲô��ÿ�㷽��������Ҫ��Щʲô�£���ʱ����
	 * ԭ��:
	 * ÿ�㷽����ȡ�����һλֵ��Ȼ���ʣ��ֵ������һ�㣬ֱ��ʣ��ֵΪ��λʱ���ظ��ϲ㣬�ϲ㽫�Լ�ȡ�������һλֵ���²�ƴ�Ӳ�����
	 * @param ÿ��ȡ�����һλ��ʣ��ֵ
	 * @return �������һλ���²㷵��ֵ��ƴ�ӣ����һ��ֱ�ӷ��ص�ǰֵ.
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
