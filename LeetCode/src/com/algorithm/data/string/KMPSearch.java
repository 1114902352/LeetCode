package com.algorithm.data.string;

import com.alibaba.fastjson.JSONObject;

public class KMPSearch {

    /**
     * 构造next数组
     * i为靠前的指标，默认为0
     * j为靠后的指标，默认为1
     * next[0] = -1
     * 从j=1开始遍历模式串
     * 如果p[i]=p[j],则next[j++] = i++，即next指标j的下一个元素等于next[j]+1
     * 如果p[i]!=p[j],则i=next[i]，即寻找一个更小的相同前缀后缀
     */
    private static int[] getNext(char[] pattern){
        int[] next = new int[pattern.length];
        next[0] = -1;
        int i = 0,j=1;
        while(j < next.length-1) {
            if(i == -1 || pattern[i] == pattern[j]){
                i++;// 即在 P0~Pi的长度上+1
                j++;// 填写到下一位置
                next[j] = i;
            }else{
                i = next[i];
            }
        }
        return next;
    }

    public static int indexOf(String targetStr,String patternStr){
        char[] target = targetStr.toCharArray();
        char[] pattern = patternStr.toCharArray();
        int[] next = getNext(pattern);
        System.out.println(JSONObject.toJSONString(next));
        int i = 0;
        int j = 0;
        while(i < target.length){
            if(j == -1 || target[i] == pattern[j]){
                i++;
                j++;
            }else{
                j = next[j];
            }
            if(j >= pattern.length){
                return i - pattern.length;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        String str1 = "abacabcabccaaabcde";
//        String str2 = "abcabc";
//        int res1 = KMPSearch.indexOf(str1, str2);
//        int res2 = str1.indexOf(str2);
//        System.out.println(res1+"=="+res2);
//        String str3 = "ababcabc";
//        int[] res1 = KMPSearch.getNext(str3.toCharArray());
//        int[] res2 = KMPSearch.test(str3.toCharArray());
//        System.out.println(JSONObject.toJSONString(res1)+"=="+JSONObject.toJSONString(res2));

    }
}
