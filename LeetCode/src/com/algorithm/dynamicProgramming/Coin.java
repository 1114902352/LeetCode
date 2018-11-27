package com.algorithm.dynamicProgramming;

import com.alibaba.fastjson.JSONObject;

/**
 * 如果我们有面值为1元、3元和5元的硬币若干枚，如何用最少的硬币凑够11元？
 */
public class Coin {

    /**
     * 设d(i)=j,表示凑够i元最少需要j个硬币(拿coin种类为{1,3,5}举例)
     * d(0)=0
     * d(1)=d(1-1)+1=1
     * d(2)=d(2-1)+1=2
     * d(3)=Min{d(3-1)+1,d(3-3)+1}=1
     * d(4)=Min{d(4-1)+1,d(4-3)+1}=2
     * d(5)=Min{d(5-1)+1,d(5-3)+1,d(5-5)+1}=1
     * ...
     * d(i)=Min{d(i-vj)+1},其中i-vj>=0,vj表示第i个硬币的面值
     */
    public static int minCoin(int sum) {
        int[] coin = {2, 4};
        int[] d = new int[sum + 1];
        for (int i = 0; i <= sum; ++i) {
            for (int j = 0; j < coin.length; ++j) {
                if ((coin[j] <= i) && ((d[i - coin[j]] + 1) < i)) {
                    d[i] = d[i - coin[j]] + 1;
                }
            }
        }
        return d[sum];
    }

    public static int minCoin(int num,int[] coins){
        int[] dp = new int[num+1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int min = 999;// 初始化一个最小值，可能有无法凑出的情况，所以此处指定一个特殊的数
            for (int j = 0; j < coins.length; j++) {// 遍历硬币种类
                if(i - coins[j] >= 0 && dp[i-coins[j]] + 1 < min){
                    min = dp[i-coins[j]] + 1;
                }
            }
            dp[i] = min;
        }
        System.out.println(JSONObject.toJSONString(dp));
        return dp[num];
    }

    public static void main(String[] args) {
        int num = 11;
        int[] temp = new int[num+1];
        for (int i = 0; i <= num; i++) {
            temp[i] = i;
        }
        System.out.println(JSONObject.toJSONString(temp));
        System.out.println(Coin.minCoin(num,new int[]{2,4}));
    }
}
