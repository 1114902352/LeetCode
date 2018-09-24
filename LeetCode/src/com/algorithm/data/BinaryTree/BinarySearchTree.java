package com.algorithm.data.BinaryTree;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉查找树或者是一棵空树，或者是具有下列性质的二叉树：
 * （1）若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * （2）若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
 * （3）左、右子树也分别为二叉查找树；
 * （4）没有键值相等的节点
 */
public class BinarySearchTree {

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
     * 无递归遍历
     */
    public static List<Integer> traversalWithoutRecursive(TreeNode root,String flag) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null){
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(!stack.empty() || p != null){
            if(p!=null){
                stack.push(p);
                if(flag.equals("先序")){
                    ret.add(p.val);
                }
                p = p.left;
            }else{
                p = stack.pop();
                if(flag.equals("中序")){
                    ret.add(p.val);
                }
                p = p.right;
            }
        }
        return ret;
    }

    /**
     * 后序访问
     */
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> alist = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null)
            return alist;
        TreeNode cur,pre = null;
        stack.push(root);
        while(!stack.empty()){
            cur = stack.peek();
            if((cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre))){
                TreeNode temp = stack.pop();
                alist.add(temp.val);
                pre = temp;
            }
            else{
                if(cur.right != null)
                    stack.push(cur.right);
                if(cur.left != null)
                    stack.push(cur.left);
            }
        }
        return alist;
    }

    /**
     * 二叉搜索树的最小祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        if ((root.val >= p.val && root.val <= q.val) || (root.val >= q.val && root.val <= p.val)) {
            return root;
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return null;
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

    /**
     * 根据数组 创建完全二叉树
     * @param array
     * @return
     */
    public static TreeNode createBinaryTree(Integer[] array){
        if(array == null || array.length < 1){
            return null;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        for (Integer val: array) {
            if(val == null){
                continue;
            }
            nodeList.add(new TreeNode(val));
        }
        for (int i = 0; i <= nodeList.size() / 2 - 1; i++) {
            TreeNode TreeNode = nodeList.get(i);
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            TreeNode.left = leftIndex >= nodeList.size() ? null : nodeList.get(leftIndex);
            TreeNode.right = rightIndex >= nodeList.size() ? null : nodeList.get(rightIndex);
        }
        return nodeList.get(0);
    }

    /**
     * 打印树(只能打出层级关系，无法区分左右节点)
     * @param root
     */
    private static void printTree(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();

        int current;//当前层 还未打印的结点个数
        int next;//下一层结点个数

        queue.offer(root);
        current = 1;
        next = 0;
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.printf("%-4d", currentNode.val);
            current--;

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
                next++;
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
                next++;
            }
            if (current == 0) {
                System.out.println();
                current = next;
                next = 0;
            }
        }

    }

    public static void main(String[] args) {
//        Integer[] array1 = {1,null,2,3};
//        Integer[] array2 = {0,1,2,3,4,5};
        Integer[] array3 = {5,3,8,1,4,6,9};
        TreeNode root = createBinaryTree(array3);
        printTree(root);

//        System.out.println("===================遍历");
//        System.out.println(TreeNode.scanNodes(root,"中序"));
//        List<Integer> scanRes = BinarySearchTree.traversalWithoutRecursive(root,"中序");
//        System.out.println(JSONObject.toJSONString(scanRes));
//
//        System.out.println("===================最小共同祖先");
//        TreeNode ancestor = BinarySearchTree.lowestCommonAncestor(root, new TreeNode(7), new TreeNode(8));
//        System.out.println(JSONObject.toJSONString(ancestor));

        System.out.println("====================二叉搜索树添加节点");
        TreeNode res = BinarySearchTree.addNode(root, 2);
        printTree(res);
    }
}
