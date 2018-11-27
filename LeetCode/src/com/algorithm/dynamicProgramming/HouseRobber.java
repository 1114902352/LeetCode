package com.algorithm.dynamicProgramming;

public class HouseRobber {

    /**
     * house robber
     * 在一列数组中取出一个或多个不相邻数，使其和最大
     * dp[i]表示到i位置时，不相邻数能形成的最大和
     * 状态转移方程 dp[i] = max(num[i] + dp[i - 2], dp[i - 1])
     * @param nums 目标数组
     * @return 最大值
     */
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2],dp[i-1]);
        }
        return dp[nums.length-1];
    }

}
