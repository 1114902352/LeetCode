package com.algorithm.data.string;

public class IntToString {

    /**
     * 建立一个数表，每次通过查表找出当前最大的数，减去再继续查表。参见代码如下：
     */
    public String intToRoman(int num) {
        String res = "";
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < val.length; ++i) {
            while (num >= val[i]) {
                num -= val[i];
                res += str[i];
            }
        }
        return res;
    }
}
