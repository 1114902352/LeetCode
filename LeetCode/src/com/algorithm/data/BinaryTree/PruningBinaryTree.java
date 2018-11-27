package com.algorithm.data.BinaryTree;

/**
 * 给予一个0或者1值的二叉树，要求修剪值全是0的子树，叶子节点为0也被当成全是0的子树
 */
public class PruningBinaryTree {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0){
            root = null;
        }
        return root;
    }
}
