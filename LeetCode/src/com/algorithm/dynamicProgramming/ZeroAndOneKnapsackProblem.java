package com.algorithm.dynamicProgramming;

import com.alibaba.fastjson.JSONObject;

public class ZeroAndOneKnapsackProblem {


//    Input: nums is [1, 1, 1, 1, 1], S is 3.
//    Output: 5
//    Explanation:
//            -1+1+1+1+1 = 3
//            +1-1+1+1+1 = 3
//            +1+1-1+1+1 = 3
//            +1+1+1-1+1 = 3
//            +1+1+1+1-1 = 3
//    sum（正） - sum（负） = target
//    两边都加上同样的sum（正） + sum（负）：
//    sum（正） + sum（负） + sum（正） - sum（负） = target + sum（正） + sum（负）
//    化简得：
//    2 * sum（正） = target + sum（正） + sum（负） = target + sum（数组总和）
    /**
     * 给予一个数组和目标数，用+和-计算数组中的元素，使之结果等于目标数S
     * 根据以上推导公式 2sum(正)=target+sum(总)可以将该题简化为
     *      有多少种方式可以从数组中拿出的数等于(target+sum(总))/2
     * @param nums 源数组
     * @param S 目标数值
     * @return 结果
     */
    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 所有和加起来都无法达到目标数
        if(sum < S){
            return 0;
        }
        // 无法找到合适的子序列
        if((S + sum) % 2 > 0){
            return 0;
        }
        int target = (S + sum) / 2;
        return SumToTarget.sumToTarget2(nums,target);
    }


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
        int[] nums = {0,0,0,0,0,0,0,0,1};
        int target = 1;
        findTargetSumWays(nums,target);
    }
}
