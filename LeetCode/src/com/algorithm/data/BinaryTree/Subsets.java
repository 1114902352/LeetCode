package com.algorithm.data.BinaryTree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//        Input: nums = [1,2,3]
//        Output:
//        [
//        [3],
//        [1],
//        [2],
//        [1,2,3],
//        [1,3],
//        [2,3],
//        [1,2],
//        []
//        ]
/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * 给定一个数组，列出所有有可能的子序列
 */
public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> current;
        for (int i = 0; i < nums.length; i++) {
            int size = ret.size();
            if(size > 0){
                for (int j = 0; j < size; j++) {
                    current = new ArrayList<>(ret.get(j));
                    current.add(nums[i]);
                    ret.add(current);
                }
            }
            ret.add(Arrays.asList(nums[i]));
        }
        ret.add(new ArrayList<>());
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(JSONObject.toJSONString(subsets(nums)));
    }



}
