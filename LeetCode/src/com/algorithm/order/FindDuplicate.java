package com.algorithm.order;

//	Given an array nums containing n + 1 integers
//  where each integer is between 1 and n (inclusive),
//  prove that at least one duplicate number must exist.
//  Assume that there is only one duplicate number, find the duplicate one.
//
//	Note:
//	You must not modify the array (assume the array is read only).
//	You must use only constant, O(1) extra space.
//	Your runtime complexity should be less than O(n2).
//	There is only one duplicate number in the array, but it could be repeated more than once.
public class FindDuplicate {

	/**
	 * 二分法查找
	 * 如果一个数组的元素范围在[1,n]之间，此时的数组却有n+1个数，那么这个数组中一定有重复元素
	 * [1,2,3,4,5,3]
	 * 如上，计算一个中间数3，正常情况下，比3小或等于的数只会有三个，但是此时却有[1,2,3,3]四个值，
	 * 说明重复的数一定是在[1,3]这个范围内，此时再取中间数，继续缩小范围
	 *
	 * @param nums 目标数组
	 * @return 重复元素
	 */
	public static int findDuplicate1(int[] nums) {
		int min = 0, max = nums.length - 1;
		while(min <= max){
			// 找到中间那个数
			int mid = min + (max - min) / 2;
			int cnt = 0;
			// 计算总数组中有多少个数小于等于中间数
			for(int i = 0; i < nums.length; i++){
				if(nums[i] <= mid){
					cnt++;
				}
			}
			// 如果小于等于中间数的数量大于中间数，说明前半部分必有重复
			if(cnt > mid){
				max = mid - 1;
				// 否则后半部分必有重复
			} else {
				min = mid + 1;
			}
		}
		return min;
	}


//	 0 1 2 3 4 5 6 7
//	[4,6,2,1,7,1,3,5]
//						-----3
//						|	 |
//	0 -> 4 -> 7 -> 5 -> 1 -> 6
	/**
	 * 数组元素值表示下一个节点在数组中的位置
	 * 如上数组即可构成一个有环的链表
	 * @param nums 目标数组
	 * @return 重复数字
	 */
	public static int findDuplicate2(int[] nums) {
		int slow = 0;
		int fast = 0;
		// 找到快慢指针相遇的地方
		do{
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while(slow != fast);
		int find = 0;
		// 用一个新指针从头开始，直到和慢指针相遇
		while(find != slow){
			slow = nums[slow];
			find = nums[find];
		}
		return find;
	}

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,3};
		System.out.println(findDuplicate1(nums));
	}
}
