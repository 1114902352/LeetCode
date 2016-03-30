package com.algorithm.order;

import java.util.HashSet;
import java.util.Set;

public class Duplicate {
	
	/**
	 * ���ϵ��������Ѿ������ˣ�
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
	
	/**
	 * ʱ�临�Ӷ�̫�ߣ�������leetcode��׼
	 * @param nums
	 * @return
	 */
	public static boolean containsDuplicate2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] == nums[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,1,3,5};
		System.out.println(containsDuplicate2(nums));
	}
}
