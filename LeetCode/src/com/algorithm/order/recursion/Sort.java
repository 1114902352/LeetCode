package com.algorithm.order.recursion;

/**
 * ���������ƶ������ O(1)�������ģnֵ�仯���仯����һ�������׺���
 * ��(1)����(log2n)����(n)����(nlog2n)����(n2)����(n3)��������(2n)����(n!)
 */
public class Sort {

	/**
	 * ʹ�õ�ָ�룺O(2n)
	 * ԭ��ָ��Pͣ�ڵ�һ�����ϣ�ѭ���ҵ�����ֵ��������ֵ���ǵ���ֵ�ϣ�Ȼ��ָ��ݽ�
	 * ע�⣺��Ҫ�ڶ���ѭ����ָ�뼰ָ����ֵȫ����Ϊ��
	 */
	public static void moveZeroes(int[] nums) {
		int pos = 0;
		// ����0���ֶ���������ǰ��
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[pos] = nums[i];
				pos++;
			}
		}
		// ��ʣ��Ķ���0
		for (; pos < nums.length; pos++) {
			nums[pos] = 0;
		}
	}

	/**
	 * ʹ������ָ�룺O(n) ԭ����ʼʱ����ָ��p1,p2��ָ��ͬһ��ֵ��ͬʱ�ݽ���ֱ��ֵΪ��ʱ��p1ͣ�����ϣ�p2�ݽ���ȥѰ����һ����Ϊ���ֵ
	 * �ҵ���ִ��p1.value=p2.value��p2.value=0��Ȼ��p1��p2ͬʱ�ݽ�ֱ����һ����������
	 * ���գ�p1��Զָ���һ����ֵ��p2�ݽ��ҵ�������ֵ����p1ֵ�滻
	 */
	public static void moveZeroes2(int[] nums) {
		int i = 0;
		int j = 0;
		while (j < nums.length) {
			if (nums[j] != 0) {
				if (j != i) {// P1�Ѿ�ͣ����ֵ�ϣ�����P2ָ��һ������ֵ����ʱӦ���滻���滻��ɺ�P1,P2�����ݽ�
					nums[i] = nums[j];
					nums[j] = 0;
					++i;
				}else{// ������֮ǰ������ָ�붼ָ��ͬһ��ֵ��ͬʱ�ݽ�
					++i;
				}
			}
			++j;// P2ָ��0ʱ��P1ͣ�£�P2�ݽ�
		}
	}

	public static void main(String[] args) {
		 int[] nums = {1,2,0,3,0,4,5,6,0};
//		int[] nums = { 0, 0, 1, 0, 0, 0, 2 };
		 moveZeroes2(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + "\t");
		}
	}
}
