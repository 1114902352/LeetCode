package com.algorithm.data.BinaryTree;

public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        return invoke(nums,0,nums.length-1);
    }

    private TreeNode invoke(int[] nums,int i,int j){
        int maxIndex = this.getMaxIndex(nums, i, j);
        TreeNode left = null;
        if(i <= maxIndex-1){
            left = invoke(nums,i,maxIndex-1);
        }
        TreeNode right = null;
        if(maxIndex+1 <= j){
            right = invoke(nums,maxIndex+1,j);
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = left;
        root.right = right;
        return root;
    }

    private Integer getMaxIndex(int[] nums,int i,int j){
        if(i==j){
            return i;
        }
        int max = i;
        for (i = i + 1; i <= j; i++) {
            if(nums[max] < nums[i]){
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumBinaryTree util = new MaximumBinaryTree();
        int[] nums = {3,2,1,6,0,5};
        System.out.println(util.constructMaximumBinaryTree(nums));
//        System.out.println(JSONObject.toJSONString());
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}


