package com.algorithm.data.BinaryTree;


import com.alibaba.fastjson.JSONObject;

public class Test {

    /**
     * 给定一个非空正整数组，判断是否能够找到两个子序列，使两个子序列的所有元素之和相等
     * Sum(分区1)=Sum(分区2)
     * Sum(总)=Sum(分区1)+Sum(分区2)=2*Sum(分区1)
     * 所以判断是否能够找到两个分区的关键是：
     * 从源数组中找到一个子序列使Sum(子序列)=Sum(总)/2
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= 0; j--) {
                if (j < num) {
                    break;
                }
                dp[j] = dp[j] || dp[j - num];
            }
        }
        System.out.println(JSONObject.toJSONString(dp));
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
        System.out.println(canPartition(nums));
    }
}
