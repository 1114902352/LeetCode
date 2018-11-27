package com.algorithm.order.ReconstructQueue;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Comparator;

public class ReconstructQueue {

    /**
     *
     * 假设你有一个站在队列中的人的随机列表。每人用一对整数(h，k)来描述，其中h是人的高度，k是该人前面的人数，其高度大于或等于h。
     * @param people 人队列
     * @return 排序结果
     */
    public static int[][] reconstructQueue(int[][] people) {
        // 按照H从大到小的顺序排序，如果H值相同，那么k值小的在前面
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o2[0] != o1[0]){
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        });
        // 从i=1开始遍历，将从temp[1]开始到i之前的元素全部往后移，腾出空位，之后people[temp[1]] = temp;
        // 也就是说，将当前元素temp插入到下标为temp[i]的位置，该位置之后(包含该位置)的值全部往后挪
        for (int i = 1; i < people.length; i++) {
            if(i == people[i][1]){
                continue;
            }
            int[] temp = people[i];
            for (int j = i; j > temp[1]; j--) {
                people[j] = people[j-1];
            }
            people[temp[1]] = temp;
        }
        return people;
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        reconstructQueue(people);
        System.out.println(JSONObject.toJSONString(people));
    }
}
