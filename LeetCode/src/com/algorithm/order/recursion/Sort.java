package com.algorithm.order.recursion;

/**
 * 将所有零移动到最后 O(1)，不随规模n值变化而变化，是一个常数阶函数
 * Ο(1)＜Ο(log2n)＜Ο(n)＜Ο(nlog2n)＜Ο(n2)＜Ο(n3)＜…＜Ο(2n)＜Ο(n!)
 */
public class Sort {

	/**
	 * 使用单指针：O(2n)
	 * 原理：指针P停在第一个零上，循环找到非零值，将非零值覆盖到零值上，然后指针递进
	 * 注意：需要第二次循环将指针及指针后的值全部置为零
	 */
	public static void moveZeroes(int[] nums) {
		int pos = 0;
		// 将非0数字都尽可能向前排
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[pos] = nums[i];
				pos++;
			}
		}
		// 将剩余的都置0
		for (; pos < nums.length; pos++) {
			nums[pos] = 0;
		}
	}

	/**
	 * 使用两个指针：O(n) 原理：开始时两个指针p1,p2都指向同一个值，同时递进，直到值为零时，p1停在零上，p2递进，去寻找下一个不为零的值
	 * 找到后执行p1.value=p2.value，p2.value=0，然后p1，p2同时递进直到下一次再遇到零
	 * 最终，p1永远指向第一个零值，p2递进找到不是零值，与p1值替换
	 */
	public static void moveZeroes2(int[] nums) {
		int i = 0;
		int j = 0;
		while (j < nums.length) {
			if (nums[j] != 0) {
				if (j != i) {// P1已经停在零值上，并且P2指向一个非零值，此时应该替换，替换完成后，P1,P2继续递进
					nums[i] = nums[j];
					nums[j] = 0;
					++i;
				}else{// 遇到零之前，两个指针都指向同一个值并同时递进
					++i;
				}
			}
			++j;// P2指向0时，P1停下，P2递进
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
