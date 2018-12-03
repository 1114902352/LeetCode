package com.algorithm.dynamicProgramming;

import com.algorithm.data.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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

    public int rob(TreeNode root) {
        int[] ret = postorder(root);
        return ret[0] > ret[1] ? ret[0] : ret[1];
    }

    /**
     * 后序遍历树，每层递归返回一个包含两个元素的数组
     * root[0]表示加上当前值的最大收益
     * root[1]表示不加上当前值的最大收益
     * 由于是后序遍历，所以
     * 加上当前值，即子节点不能要root[0]=left[1]+right[1]+root.val
     * 不加上当前值，即取子节点root[1]=Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
     */
    private int[] postorder(TreeNode root){
        int[] ret = new int[2];
        if(root == null){
            return ret;
        }
        int[] left = postorder(root.left);
        int[] right = postorder(root.right);
        ret[0] = left[1] + right[1] + root.val;// 加上当前值，需要加上不包含子节点的最大值
        // 不加上当前值，需要判断取子节点的最大值和不取子节点的最大值，取大的一个左右子节点相加
        ret[1] = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return ret;
    }
}
