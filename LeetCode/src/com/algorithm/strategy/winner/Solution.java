package com.algorithm.strategy.winner;

/**
 *	leetcode ��ʯ����Ϸ������������ÿ��ֻ����1-3�����õ����һ�ε���Ӯ
 *
 */
public class Solution {

	/**
	 * �����۾�����Ŀ��
	 * �������Ŵ�1����ʼ��Ӯ�Ĳ��ԣ�����
	 * ʯ��		Ӯ��
	 * 1		����
	 * 2		����
	 * 3		����
	 * 4		����
	 * 5		����
	 * 6		����
	 * 7		����
	 * 8		����
	 * 9		����
	 * @param ʯ������
	 * @return �����Ƿ���Ӯ
	 */
	public static boolean canWinNim(int n) {
		if(n % 4 == 0)  return false;
        else    return true;
	}
	
	public static void main(String[] args) {
		Solution.canWinNim(10);
	}
}
