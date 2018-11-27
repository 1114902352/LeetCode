package com.algorithm.data.BinaryTree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

//        Input: [1,2,3]
//        Output:
//        [
//        [1,2,3],
//        [1,3,2],
//        [2,1,3],
//        [2,3,1],
//        [3,1,2],
//        [3,2,1]
//        ]
/**
 * 给定一个数组，求出所有可能的序列
 */
public class Permute {

    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new ArrayList<>();
        int len = num.length;
        if (len == 0) {
            return res;
        }
        boolean[] visit = new boolean[len];
        List<Integer> list = new ArrayList<>();
        dfs(num, list, visit, res);
        return res;
    }

    public static void dfs(int[] nums, List<Integer> list, boolean[] visit, List<List<Integer>> res){
        // 递归终点
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visit[i]){
                continue;
            }
            list.add(nums[i]);
            visit[i] = true;
            dfs(nums,list,visit,res);
            list.remove(list.size() - 1);
            visit[i]=false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> res = permute(nums);
        System.out.println(JSONObject.toJSONString(res));
    }

}
