package com.algorithm.dynamicProgramming;

//        Input: 3
//        Output: 5
//        Explanation:
//        Given n = 3, there are a total of 5 unique BST's:
//
//        1         3     3      2      1
//        \       /     /      / \      \
//        3     2     1      1   3      2
//        /     /       \                 \
//        2     1         2                 3
/**
 * dp[n],表示只有n个元素能够构成BST的情况
 * dp[0] = 1,空树
 * dp[1] = 1,单节点
 * dp[2] = dp[0]*dp[1]   (1为根，左子树为空数dp[0]，右子树只有一个数dp[1])
 *         + dp[1]*dp[0] (2为根，左子树只有一个数dp[1]，右子树为空树)
 * dp[3] = dp[0]*dp[2]   (1为根，左子树为空树dp[0]，右子树有两个数dp[2])
 *         + dp[1]*dp[1] (2为根，左子树只有一个数dp[1]，右子树只有一个数dp[1])
 *         + dp[2]*dp[0] (3为根，左子树有两个数dp[2],右子树为空树dp[0])
 * dp[n]=∑(dp[i-1]*dp[n-i]) i=[1,n],从1到n分别作为根;n>=2 (n=0,n=1是一个固定值)
 * 给定一组数[1,n]，问有多少种组成二叉搜索树的可能情况
 */
public class UniqueBinarySearchTrees {

    /**
     * 定义dp[n]，表示[0,n]个元素能够构成的不同BST的数量
     * 特殊情况下:
     * dp[0]=1，空树也是BST
     * dp[1]=1，单节点也是BST
     * dp[2]=2,根据二叉树定义，所有左子树小于根节点，所有右子树大于根节点
     * ...
     * dp[i]=dp[i-1]*dp[n-i]，前i个元素构成BST的情况，
     * 要求左子树[1,i-1]和右子树[i+1,n]都能够构成BST
     * [1,i-1]和[i+1,n]都是有序的，所以在求能够构成BST的情况时，可以不关注具体的值，只关注个数
     * dp[i-1]表示(i-1)个数能够构成BST的情况
     * dp[n-i]表示n-(i+1)+1个数能够构成BST的情况
     * 左右子树相互独立，则dp[n]=dp[i-1]*dp[n-i],此时得出递推关系
     *
     * dp[0]=1,dp[1]=1
     * dp[n]=∑(dp[i-1]*dp[n-i]) n>=2,i=[1,n]
     * @param n 组成树的值[1,n]
     * @return 构成二叉树的可能情况
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        // dp[i]表示用i个元素构成BST的情况
        for (int i = 2; i <= n; i++) {
            // 分别以j作为根节点
            for (int j = 1; j <= i; j++) {
                dp[i] = dp[i] + dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
