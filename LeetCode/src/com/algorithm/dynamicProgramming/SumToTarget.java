package com.algorithm.dynamicProgramming;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumToTarget {

    //    Input: candidates = [2,3,6,7], target = 7,
//    A solution set is:
//            [
//            [7],
//            [2,2,3]
//            ]

    /**
     * 给定一个不重复的数组和一个目标值，求从数组中求唯一组合使该组合相加等于目标值，
     * 其中相同元素可以重复使用
     * 注意：上述例子中的组合223,232,322只能有一个结果
     *
     * @param candidates 候选数组
     * @param target     目标值
     * @return 可能的结果
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, ret, new ArrayList<>(), 0, 0);
        return ret;
    }

    /**
     * 深度遍历
     *
     * @param candidates 源数组
     * @param target     目标数字
     * @param ret        结果
     * @param res        结果中的元素
     * @param sum        和
     * @param index      上层递归下标
     */
    public void dfs(int[] candidates, int target, List<List<Integer>> ret, List<Integer> res, int sum, int index) {
        if (sum == target) {
            ret.add(new ArrayList<>(res));
            return;
        }
        // 每次遍历需要从上层节点的下标开始，防止出现重复的子序列 比如 223,232,322,这些序列应当是一个结果
        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            if (sum > target) {
                break;
            }
            res.add(candidates[i]);
            dfs(candidates, target, ret, res, sum, i);
            res.remove(res.size() - 1);
            sum = sum - candidates[i];
        }
    }

    /**
     * 在一个数组中找出一部分元素的和 等于一个目标值
     * dp[i][j]表示第i个元素能够构成目标值j的方法个数
     * dp[i][j] = dp[i-1][j] + dp[i-1][j-num[i]]
     * 即不取i元素，能够成目标值j的方法 加上取i的值能够构成目标值j的方法，
     * 其中i的范围=[0,nums.length]，i=0时，表示不取任何元素能够凑成j值的方法，只有j=0时，才有一种
     */
    public static int sumToTarget1(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        // 不取任何元素能够凑成0的方法只有一种
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                // 当前元素
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        print(dp, target);
        return dp[nums.length][target];
    }

    /**
     * 打印dp数组
     *
     * @param dp     dp数组
     * @param target 目标数
     */
    private static void print(int[][] dp, int target) {
        System.out.print("标题头\t");
        for (int i = 0; i < target + 1; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            System.out.print("第" + i + "数\t");
            for (int column : dp[i]) {
                System.out.print(column + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 在一个数组中找出一部分元素的和 等于一个目标值
     * dp[j]表示从数组中找到dp[j]中方式能够达到i值
     * 可以发现sumToTarget1的状态转移方程为
     * dp[i][j]=dp[i-1][j]+dp[i-1][j-num[i]]，该状态转移方程始终与dp[i-1][X]相关，即前一次的某个目标值
     */
    public static int sumToTarget2(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            // dp[i-1][X]表示的上一次的值，所以必须从后往前遍历，不能使当前值覆盖前一次的值
            for (int j = target; j >= 0; j--) {
                // 目标值比当前元素小且目标值是在递减，可以直接退出
                if (j < num) {
                    break;
                }
                dp[j] = dp[j] + dp[j - num];
            }
        }
        System.out.println(JSONObject.toJSONString(dp));
        return dp[target];
    }

    /**
     * 在一个数组中找出一部分元素的和 等于一个目标值
     * dp[j]表示从数组中找到dp[j]中方式能够达到i值
     */
    public static int sumToTarget(int[] nums, int index, int target) {
        System.out.println("i="+(index-1)+",j="+target);
        if (index == 0) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int ret = sumToTarget(nums, index - 1, target);
        if (target >= nums[index-1]) {
            ret += sumToTarget(nums, index - 1, target - nums[index-1]);
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 5, 3, 4, 2};
        int target = 3;
//        int[] nums = {0,0,0,0,0,0,0,0,1};
//        int target = 1;
        System.out.println(sumToTarget1(nums, target));
        System.out.println(sumToTarget2(nums, target));
        System.out.println(sumToTarget(nums,nums.length,target));
    }
}
