package com.algorithm.dynamicProgramming;


public class Subsequence {

    /**
     * 最长增长子序列
     * 设第i行的最长增长子序列长度为d(i),另外，LIS表示其最长增长子序列
     * 例如序列 5 3 4 8 6 7，那么
     * d(1)=Max{0}+1=1 LIS={5}
     * d(2)=Max{0}+1=1 LIS={3}
     * d(3)=Max{d(2)}+1=2 LIS={3,4}
     * d(4)=Max{d(1),d(2),d(3)}+1=3 LIS={3,4,8}
     * d(5)=Max{d(1),d(2),d(3)}+1=3 LIS={3,4,6}
     * d(6)=Max{d(1),d(2),d(3),d(5)}+1=4 LIS={3,4,6,7}
     * ...
     * d(i)=Max{0, d(j)}+1,其中j<i,A[j]<=A[i]
     */
    public static int longestIncreasingSubsequence(int[] nums,int n){
        int[] maxLength = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            maxLength[i]=1;
            for (int j = 0; j < i; j++) {
                if(nums[j]<=nums[i]){
                    maxLength[i] = maxLength[i] < maxLength[j] + 1 ? maxLength[j] + 1 : maxLength[i];
                }
            }
        }
        int ret = 0;
        for (int i = 0; i <maxLength.length ; i++) {
            if(maxLength[i] > ret){
                ret = maxLength[i];
            }
        }
        return ret;
    }


}
