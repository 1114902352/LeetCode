package com.algorithm.order;


/**
 * S和T是由小写字母组成的字符串。 在S中，没有一个字母出现过多次。
 * S之前以某种自定义顺序排序。 我们想要置换T的字符，使它们与S排序的顺序相匹配。 更具体地说，如果x出现在S之前的y之前，那么x应该在返回的字符串中的y之前出现。
 * 返回满足此属性的T（作为字符串）的任何排列。
 */
public class CustomSortString {

    /**
     * @param S 没有重复的字符
     * @param T 有可能有重复的字符
     * @return
     */
    public static String customSortString(String S, String T) {
        char[] sc = S.toCharArray();
        char[] tc = T.toCharArray();
        int[] dictionary = new int[26];
        for (char c : sc) {
            dictionary[c-'a']+=1;
        }
        StringBuilder extra = new StringBuilder();
        for (char c : tc) {
            if(dictionary[c-'a'] < 1){// 小于1，说明不存在S中
                extra.append(c);
            }else{                    // 大于1，c在 S中存在
                dictionary[c-'a']+=1; // 说明:  设x=dictionary[c-'a'],如果x>1,则T中有x-1个相同元素c，且在S中存在
            }
        }
        StringBuilder ret = new StringBuilder();
        for (char c : sc) {
            int temp = dictionary[c-'a'];
            if(temp > 1){
                for(int i=1;i<temp;i++){
                    ret.append(c);
                }
            }
        }
        return ret.append(extra).toString();
    }

    public static void main(String[] args) {
        String s = "cba";
        String t = "ghcabcdaabcef";
        System.out.println(CustomSortString.customSortString(s,t));
    }
}
