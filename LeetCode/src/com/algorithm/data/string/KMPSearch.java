package com.algorithm.data.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

//  -1 0 0 1 1 2 0
//   a b a a b c a
//   i j
// i   j               不匹配,i=next[i]=-1
//   i   j             i++,j++,next[j]=i    =>next[2]=0
//     i   j           i++,j++,next[j]=i    =>next[3]=1
//   i     j           不匹配,i=next[i]=0
//     i     j         i++,j++,next[j]=1    =>next[4]=1
//       i     j       i++,j++,next[j]=2    =>next[5]=2
//   i         j       不匹配,i=next[i]=0
// i           j       不匹配,i=next[j]=-1
//   i            j    i++,j++,next[j]=0    =>next[6]=0
public class KMPSearch {

    /**
     * 构造next数组，表示模式串不匹配时，指向模式串的指针应跳往的位置
     * i为靠前的指标，默认为0
     * j为靠后的指标，默认为1
     * next[0] = -1，next[1] = 0
     * 从j=1开始到next.length-1遍历模式串，且j为基准，始终往后移动
     *
     * 如果p[i]=p[j],则next[j++] = i++，即next指标j的下一个元素等于next[j]+1
     * 如果p[i]!=p[j],则i=next[i]，即寻找一个更小的相同前缀后缀
     */
    //  0 1 2 3 4 5 6 7
    //  a b c c a b c a
    //      i       j
    // 对于下标j的元素，next[j]的值实际上等于abccab的最长相同 前缀和后缀即ab，也就是说
    // 如果模式串匹配到j位置不匹配了，那它应该直接跳往i匹配，因为此时P(0~1)=P(4~5)
    private static int[] getNext(char[] pattern){
        int[] next = new int[pattern.length];
        next[0] = -1;
        next[1] = 0;
        int i = 0,j=1;
        // j 从1到next.lenght-1遍历，j为基准，始终往后移动
        while(j < next.length-1) {
            if(i == -1 || pattern[i] == pattern[j]){
                i++;// 即在 P0~Pi的长度上+1
                j++;// 填写到下一位置
                next[j] = i;
            }else{
                // a b c c a b c a
                //       i       j
                // 此时，i和j不匹配，我们可以将该模式串视作一个KMP查找
                //               j
                // a b c c a b c a  目标串
                //         a b c c  模式串
                //               i
                // abcc的next表，我们在先前已经求出，所以i和j不匹配，那么 i = next[i]
                i = next[i];
            }
        }
        return next;
    }

    /**
     * 在目标串targetStr中寻找patternStr的位置，并返回第一个字母的下标
     * @param targetStr
     * @param patternStr
     * @return
     */
    public static int indexOf(String targetStr,String patternStr){
        char[] target = targetStr.toCharArray();
        char[] pattern = patternStr.toCharArray();
        int[] next = getNext(pattern);
        System.out.println(JSONObject.toJSONString(next));
        int i = 0;
        int j = 0;
        while(i < target.length){
            // j=-1 Pattern第一个字母都不匹配，target的指标i往后移，j重置为零
            // 正确匹配，两个指标i和j都往后移
            if(j == -1 || target[i] == pattern[j]){
                i++;
                j++;
            }else{
                // 寻找下一个合适匹配
                j = next[j];
            }
            // j超过Patter串长度，匹配完成返回
            if(j >= pattern.length){
                // 此时,j = pattern.length + 1
                return i - pattern.length;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str1 = "abacabcabccaaabcde";
        String str2 = "abc";
        int res1 = KMPSearch.indexOf(str1, str2);
        int res2 = str1.indexOf(str2);
        System.out.println(res1+"=="+res2);
//        String str3 = "ababcabc";
//        int[] res1 = KMPSearch.getNext(str2.toCharArray());
//        System.out.println(JSONObject.toJSONString(res1));
//        int[] res2 = KMPSearch.test(str3.toCharArray());
//        System.out.println(JSONObject.toJSONString(res1)+"=="+JSONObject.toJSONString(res2));

    }
}
