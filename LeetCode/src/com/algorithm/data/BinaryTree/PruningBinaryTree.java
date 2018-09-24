package com.algorithm.data.BinaryTree;

public class PruningBinaryTree {

    public TreeNode pruneTree(TreeNode root) {
        invoke(root);
        return root;
    }

    public int invoke(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = invoke(root.left);
        if(left == 0){
            root.left = null;
        }
        int right = invoke(root.right);
        if(right == 0){
            root.right = null;
        }
        return left + right +root.val;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
