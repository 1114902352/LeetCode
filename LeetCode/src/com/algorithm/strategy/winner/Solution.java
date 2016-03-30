package com.algorithm.strategy.winner;

/**
 *	leetcode 拿石子游戏，两个人轮流每次只能拿1-3个，拿到最后一次的人赢
 *
 */
public class Solution {

	/**
	 * 博弈论经典题目：
	 * 可以试着从1个开始算赢的策略，例如
	 * 石子		赢家
	 * 1		先手
	 * 2		先手
	 * 3		先手
	 * 4		后手
	 * 5		先手
	 * 6		先手
	 * 7		先手
	 * 8		后手
	 * 9		先手
	 * @param 石子总数
	 * @return 先手是否能赢
	 */
	public static boolean canWinNim(int n) {
		if(n % 4 == 0)  return false;
        else    return true;
	}
	
	public static void main(String[] args) {
		Solution.canWinNim(10);
	}
}
