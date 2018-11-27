package com.algorithm.dynamicProgramming;

/**
 * 计算回文串的个数
 */
public class CountPalindromicSubstring {
//        i
//    abccba
//     j
    /**
     * 动态规划：定义dp[i][j]表示从i到j是否是回文
     * 每次外循环i往右走，内循环j往左走
     * 发现 array[i]==array[j]，则i~j之间有可能形成回文，此时分为三种情况
     * 1.  i==j，本身就是一个回文串，dp[i][j]=true
     * 2.  i==j+1，是两个紧邻的字符，dp[i][j]=true
     * 3.  以上两种都不是，就需要判断dp[i-1][j+1]的值，如果为true，则dp[i][j]=true
     */
//    for (int i = 0; i < array.length; i++) {
//        for (int j = i; j >= 0; j++) {
//
//        }
//    }
    public static int countSubstrings(String s) {
        int ret = 0;
        char[] array = s.toCharArray();
        boolean[][] dp = new boolean[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j >= 0; j--) {
                if(array[i] == array[j]){// 有可能成为回文
                    if(i == j || i == j+1){
                        dp[i][j] = true;
                        ret++;
                        continue;
                    }
                    if(dp[i-1][j+1]){
                        dp[i][j] = true;
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(countSubstrings(s));
    }

}


