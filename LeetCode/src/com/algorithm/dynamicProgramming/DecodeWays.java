package com.algorithm.dynamicProgramming;

import com.alibaba.fastjson.JSONObject;

//A message containing letters from A-Z is being encoded to numbers using the following mapping:
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//Given a non-empty string containing only digits, determine the total number of ways to decode it.
public class DecodeWays {


    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        char[] array = s.toCharArray();
        int[] dp = new int[array.length];
        dp[0] = 1;
        if(array.length >1){
            if(array[1] != '0'){
                dp[1]=1;
            }
            if(Integer.parseInt(array[0]+""+array[1]) < 27){
                dp[1]+=1;
            }
        }
        for (int i = 2; i < array.length; i++) {
            if(array[i] != '0'){
                dp[i] = dp[i-1];
            }
            if(array[i-1] != '0' && Integer.parseInt(array[i-1]+""+array[i]) < 27){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()-1];
    }
    public static void main(String[] args) {
        DecodeWays t = new DecodeWays();
        System.out.println(t.numDecodings("10"));
    }

}
