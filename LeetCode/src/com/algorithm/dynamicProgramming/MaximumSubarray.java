package com.algorithm.dynamicProgramming;

import com.alibaba.fastjson.JSONObject;

/**
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * 给定一个数组，需要找出和最大的连续子串
 */
public class MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            if (dp[i - 1] > 0) {
                dp[i]=dp[i-1]+dp[i];
            }
        }
        System.out.println(JSONObject.toJSONString(dp));
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if(max < dp[i]){
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(MaximumSubarray.maxSubArray(nums));
    }
}
