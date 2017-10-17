package com.algorithm.order;

/**
 * 变形词
 */
public class Anagram {
	
	/**
	 * 用一张字母表来存储结果
	 * 原理：题目规定已经规定只有小写的26个字母，所以可以用一张26位的字母表来表示结果，
	 * index为该字母的序号，值为出现次数，字符串1中出现字母就往字母表中加一，字符串出现字母就往字母表中减一
	 * 最后遍历判断这张表中的值是否全都是零，不是说明两个词不是变形词
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isAnagram(String s,String t){
		int[] table = new int[26];
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		if (sArr.length != tArr.length) {
			return false;
		}
		for(int i=0;i<sArr.length;i++){
			char sc = sArr[i];
			char tc = tArr[i];
			table[sc-'a']++;
			table[tc-'a']--;
		}
		for(int temp:table){
			if(temp!=0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 先排序后比较，leetcode上要求的时间复杂度太高,提交不通过
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isAnagram2(String s, String t) {
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		if (sArr.length != tArr.length) {
			return false;
		}
		for (int i = 0; i < sArr.length; i++) {
			for (int j = i + 1; j < sArr.length; j++) {
				if (sArr[i] > sArr[j]) {
					char temp = sArr[j];
					sArr[j] = sArr[i];
					sArr[i] = temp;
				}
			}
		}
		for (int i = 0; i < tArr.length; i++) {
			for (int j = i + 1; j < tArr.length; j++) {
				if (tArr[i] > tArr[j]) {
					char temp = tArr[j];
					tArr[j] = tArr[i];
					tArr[i] = temp;
				}
			}
		}
		return (new String(sArr)).equals(new String(tArr));
	}

	public static void main(String[] args) {
		String t = "abcde";
		String s = "acdbe";
		System.out.println(isAnagram2(t, s));
	}
}
