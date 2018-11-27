package com.algorithm.data.BinaryTree;

/**
 * 二叉查找树或者是一棵空树，或者是具有下列性质的二叉树：
 * （1）若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * （2）若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
 * （3）左、右子树也分别为二叉查找树；
 * （4）没有键值相等的节点
 */
public class BinarySearchTree {

    /**
     * 二叉搜索树的最小祖先，共同祖先大于其中一个目标节点且小于另外一个目标节点
     * @param root 根节点
     * @param p 目标节点1
     * @param q 目标节点2
     * @return 最小共同祖先
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 入参检查
        if(p == null || q == null){
           return null;
        }
        // 遍历到空叶子节点仍然没有找到，即返回null
        if (root == null){
            return null;
        }
        if ((root.val >= p.val && root.val <= q.val) || (root.val >= q.val && root.val <= p.val)) {
            // 找到符合条件值并将其返回
            return root;
        } else if (root.val > p.val && root.val > q.val) {
            // 当前值大于p和q的值，说明祖先在左子树上
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            // 当前值小于p和去的值，说明祖先在右子树上
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return null;
        }
    }

    public static TreeNode addNode(TreeNode root,int key){
        TreeNode target = new TreeNode(key);
        if(root == null){
            return target;
        }
        addNode(root,target);
        return root;
    }

    public boolean delete(TreeNode root,int val) {
        TreeNode currentNode = root;
        TreeNode parentNode = root;
        boolean isLeftChild = true;
        while (currentNode != null && currentNode.val != val) {
            parentNode = currentNode;
            if (val < currentNode.val) {
                currentNode = currentNode.left;
                isLeftChild = true;
            } else {
                currentNode = currentNode.right;
                isLeftChild = false;
            }
        }
        if (currentNode == null) {
            return false;
        }
        if (currentNode.left == null && currentNode.right == null) {
            //要删除的节点为叶子节点
            if (currentNode == root)
                root = null;
            else if (isLeftChild)
                parentNode.left = null;
            else
                parentNode.right = null;
        } else if (currentNode.right == null) {//要删除的节点只有左孩子
            if (currentNode == root)
                root = currentNode.left;
            else if (isLeftChild)
                parentNode.left = currentNode.left;
            else
                parentNode.right = currentNode.left;
        } else if (currentNode.left == null) {//要删除的节点只有右孩子
            if (currentNode == root)
                root = currentNode.right;
            else if (isLeftChild)
                parentNode.left = currentNode.right;
            else
                parentNode.right = currentNode.right;
        } else { //要删除的节点既有左孩子又有右孩子
            //思路：用待删除节点右子树中的key值最小节点的值来替代要删除的节点的值,然后删除右子树中key值最小的节点
            //右子树key最小的节点一定不含左子树,所以删除这个key最小的节点一定是属于叶子节点或者只有右子树的节点
            TreeNode directPostNode = getDirectPostNode(currentNode);
            currentNode.val = directPostNode.val;
        }
        return true;
    }

    private TreeNode getDirectPostNode(TreeNode delNode) {//方法作用为得到待删除节点的直接后继节点
        TreeNode parentNode = delNode;//用来保存待删除节点的直接后继节点的父亲节点
        TreeNode direcrPostNode = delNode;//用来保存待删除节点的直接后继节点
        TreeNode currentNode = delNode.right;
        while (currentNode != null) {
            parentNode = direcrPostNode;
            direcrPostNode = currentNode;
            currentNode = currentNode.left;
        }
        if (direcrPostNode != delNode.right) {//从树中删除此直接后继节点
            parentNode.left = direcrPostNode.right;
            direcrPostNode.right = null;
        }
        return direcrPostNode;//返回此直接后继节点

    }

    private static void addNode(TreeNode root,TreeNode target){
        if(target.val > root.val){
            if(root.right == null){
                root.right = target;
            }
            addNode(root.right,target);
        }else if(target.val < root.val){
            if(root.left == null){
                root.left = target;
            }
            addNode(root.left,target);
        }
    }

    /**
     * 获取目标节点路径，以字符串方式输出
     * @param root
     * @param target
     * @return
     */
    public static String getPath(TreeNode root,TreeNode target){
        if(root == null){
            return null;
        }
        if(root.val == target.val){
            return root.val+"";
        }
        String left = getPath(root.left,target);
        if(left != null){
            return left+root.val;
        }
        String right = getPath(root.right,target);
        if(right != null){
            return right+root.val;
        }
        return null;
    }

    private int sum = 0;

    /**
     * 给定二叉查找树，要求对二叉查找树上的每一个数字进行修改，其值为原值加上树中比其大的数。
     */
    public TreeNode convertBST(TreeNode root) {
        inorderTraversal(root);
        return root;
    }

    public void inorderTraversal(TreeNode root){
        // 右 根 左 的中序遍历
        if(root == null){
            return;
        }
        inorderTraversal(root.right);
        root.val += sum;
        sum = root.val;
        inorderTraversal(root.left);
    }
}
