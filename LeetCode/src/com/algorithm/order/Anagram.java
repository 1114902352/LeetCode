package com.algorithm.order;

/**
 * ���δ�
 */
public class Anagram {
	
	/**
	 * ��һ����ĸ�����洢���
	 * ԭ����Ŀ�涨�Ѿ��涨ֻ��Сд��26����ĸ�����Կ�����һ��26λ����ĸ������ʾ�����
	 * indexΪ����ĸ����ţ�ֵΪ���ִ������ַ���1�г�����ĸ������ĸ���м�һ���ַ���������ĸ������ĸ���м�һ
	 * �������ж����ű��е�ֵ�Ƿ�ȫ�����㣬����˵�������ʲ��Ǳ��δ�
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
	 * �������Ƚϣ�leetcode��Ҫ���ʱ�临�Ӷ�̫��,�ύ��ͨ��
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
