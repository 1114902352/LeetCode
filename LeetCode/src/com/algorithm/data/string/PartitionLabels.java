package com.algorithm.data.string;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {

    public List<Integer> partitionLabels(String s){
        List<Integer> ret = new ArrayList<>();
        Map<Character,Integer> lastMap = new HashMap<>();// s中每一个字符最靠右的index
        char[] sc = s.toCharArray();
        // lastMap中 key为s中的每一个char,value 是该char出现的最靠右的位置索引
        for (int i = 0; i < sc.length; i++) {
            lastMap.put(sc[i],i);
        }
        int start = 0;// 每个区域的开始index
        int last = 0;// 每个区域的结束index
        // 从0开始遍历s中的每一个char
        for(;start < sc.length;start++){
            // 找到当前char最靠右的位置，暂时作为区域划分的右边界限
            last = lastMap.get(sc[start]);
            // 此时，遍历[start,last]区域中的每一个元素，如果其中的任一元素最靠右的索引大于last，就扩大范围
            for(int i = start;i < last;i++){
                if(lastMap.get(sc[i]) > last){// 其中一个元素最靠右索引大于当前last
                    last = lastMap.get(sc[i]);// last更新为该元素的最靠右索引(扩大范围)
                }// 然后继续寻找，后面的元素的最靠右索引仍然大于，继续更新last，扩大范围
            }
            ret.add(last-start+1);// [start,last]中的所有元素都不在之后的区间出现，形成一个区域，保存区域的size
            start = last;// start更新为当前区域的最后位置，开始寻找下一个区域
        }
        return ret;
    }

    public int[] countBits(int num) {
        int[] ret = new int[num+1];
        for (int i = 1; i <= num; ++i) {
            if (i % 2 == 0) {
                ret[i] = ret[i / 2];
            } else {
                ret[i] = ret[i / 2] + 1;
            }
        }
        return ret;
    }
}

