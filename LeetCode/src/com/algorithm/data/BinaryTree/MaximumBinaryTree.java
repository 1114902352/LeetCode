package com.algorithm.data.BinaryTree;

/**
 * 这道题给了我们一个数组，让我们创建一个最大二叉树，创建规则是数组中的最大值为根结点，
 * 然后分隔出的左右部分再分别创建最大二叉树
 */
public class MaximumBinaryTree {

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        return invoke(nums,0,nums.length-1);
    }

    /**
     * 递归分治
     * @param nums 源数据
     * @param i 左边界
     * @param j 右边界
     * @return 构造的节点
     */
    private static TreeNode invoke(int[] nums,int i,int j){
        Integer maxIndex = getMaxIndex(nums, i, j);
        TreeNode current = new TreeNode(nums[maxIndex]);
        if(maxIndex > i){
            current.left = invoke(nums, i, maxIndex - 1);
        }
        if(maxIndex < j){
            current.right = invoke(nums,maxIndex+1,j);
        }
        return current;
    }

    private static Integer getMaxIndex(int[] nums,int i,int j){
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
        int[] nums = {3,2,1,6,0,5};
        TreeNode root = constructMaximumBinaryTree(nums);
        BTreePrinter.printNode(root);
    }
}


