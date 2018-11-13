package com.algorithm.dynamicProgramming;

/**
 * 如果我们有面值为1元、3元和5元的硬币若干枚，如何用最少的硬币凑够11元？
 */
public class Coin {

    /**
     * 设d(i)=j,表示凑够i元最少需要j个硬币
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
        int[] coin = {1, 3, 5};
        int[] d = new int[sum + 1];
        for (int i = 0; i <= sum; ++i) {
            d[i] = i;
            for (int j = 0; j < 3; ++j) {
                if ((coin[j] <= i) && ((d[i - coin[j]] + 1) < d[i])) {
                    d[i] = d[i - coin[j]] + 1;
                }
            }
        }
        return d[sum];
    }
}
