package com.algorithm.data.BinaryTree;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        System.out.println(JSONObject.toJSONString(nums));
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(JSONObject.toJSONString(findDisappearedNumbers(nums)));
    }

}
