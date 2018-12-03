package com.algorithm.data.BinaryTree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//		A
//	   / \
//	  B   C
/**
 * 二叉树工具
 *
 * 二叉树遍历 ，根是A，前序遍历就是ABC，中序遍历就是BAC，后序遍历就是BCA，根据A的位置决定
 * 遍历注意点：有子节点要先去找子节点，空要补足，再按上述顺序去找
 *
 * 深度优先遍历包含前序/后序/中序
 *
 * 满二叉树的定义是除了叶子结点，其它结点左右孩子都有,深度为k的满二叉树，结点数就是2的k次方减1
 * 完全二叉树：除了最大的层次即成为一颗满二叉树且层次最大那层所有的结点均向左靠齐，即集中在左面的位置上，不能有空位置。
 *
 * 由递归顺序推出二次树时，无中序无法求得，中序可分离左右，前后序确定根节点
 */
public class BinaryTreeUtil {

    /**
     * 根据数组 创建完全二叉树
     * @param array 堆
     * @return 构建完成的二叉树
     */
    public static TreeNode createBinaryTree(Integer[] array){
        if(array == null || array.length < 1){
            return null;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        for (Integer val: array) {
            if(val == null){
                nodeList.add(null);
            }else{
                nodeList.add(new TreeNode(val));
            }
        }
        for (int i = 0; i <= nodeList.size() / 2 - 1; i++) {
            TreeNode treeNode = nodeList.get(i);
            if(treeNode == null){
                continue;
            }
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            treeNode.left = leftIndex >= nodeList.size() ? null : nodeList.get(leftIndex);
            treeNode.right = rightIndex >= nodeList.size() ? null : nodeList.get(rightIndex);
        }
        return nodeList.get(0);
    }

    /**
     * 广度优先遍历 Breadth First Search
     * @param root 根节点
     * @return 遍历结果
     */
    public static String bfs(TreeNode root){
        StringBuilder ret = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p;
        queue.offer(root);
        while(!queue.isEmpty()){
            p = queue.poll();
            if(p.left != null){
                queue.offer(p.left);
            }
            if(p.right != null){
                queue.offer(p.right);
            }
            ret.append(p.val).append(" ");
        }
        return ret.toString();
    }

    /**
     * 广度优先遍历 按层级输出
     * @param root 根节点
     * @return 结果
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null){
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode current;
        List<Integer> level = new ArrayList<>();
        int curLevel = 1;// 当前层级节点数量
        int nextLevel = 0;// 下层层级节点数量
        while(!queue.isEmpty()){
            current = queue.poll();
            level.add(current.val);
            curLevel--;
            if(current.left != null){
                queue.offer(current.left);
                nextLevel++;
            }
            if(current.right != null){
                queue.offer(current.right);
                nextLevel++;
            }
            if(curLevel == 0){// 当前层级已遍历完，重置指针
                curLevel = nextLevel;
                nextLevel = 0;
                ret.add(level);
                level = new ArrayList<>();
            }
        }
        return ret;
    }

    /**
     * 非递归先序遍历1
     * @return 结果
     */
    public static List<Integer> preorder1(TreeNode root){
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p;
        stack.push(root);
        while(!stack.isEmpty()){
            p = stack.pop();
            if(p.right != null){
                stack.push(p.right);
            }
            if(p.left != null){
                stack.push(p.left);
            }
            ret.add(p.val);
        }
        return ret;
    }

    /**
     * 非递归先序遍历2
     * @return 结果
     */
    public static List<Integer> preorder2(TreeNode root){
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            while(p != null){
                stack.push(p);
                ret.add(p.val);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                p = p.right;
            }
        }
        return ret;
    }

    /**
     * 非递归的中序遍历
     * @return 结果
     */
    public static List<Integer> inorder(TreeNode root){
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        // 中序输出根节点时，stack.size == 0，所以此处循环条件需要加上p!=null
        while(p != null || !stack.isEmpty()){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.isEmpty()){
                p = stack.pop();
                ret.add(p.val);
                p = p.right;
            }
        }
        return ret;
    }

    /**
     * 非递归的后续遍历
     * @return 结果
     */
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 定义两个指针，cur 指向当前节点，pre 指向上一个出栈的节点
        TreeNode cur = root, pre = null;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()){
                TreeNode temp = stack.peek();// 出栈检查有没有右节点
                // 有右节点 且 右节点不等于已经出栈节点(即右节点没有访问过)，则当前节点指向右节点，访问右节点
                if(temp.right != null && temp.right != pre){
                    cur = temp.right;
                    continue;
                }
                // 没有右节点 或者 右节点已经被访问，则出栈当前元素
                cur = stack.pop();
                ret.add(cur.val);
                pre = cur; // pre 指向出栈节点
                cur = null;// 当前节点重置为空，防止再次寻找左节点
            }
        }
        return ret;
    }

    /**
     * 二叉树遍历 先序/中序/后序
     * @param root 当前节点
     * @param flag 排序规则的标识
     * @return 输出字符串
     */
    public static String scanNodes(TreeNode root, String flag) {
        if (root == null) {
            return " ";
        }
        if ("先序".equals(flag)) {
            return root.val + scanNodes(root.left, flag) + scanNodes(root.right, flag);
        } else if ("中序".equals(flag)) {
            return scanNodes(root.left, flag) + root.val + scanNodes(root.right, flag);
        } else {
            return scanNodes(root.left, flag) + scanNodes(root.right, flag) + root.val;
        }
    }

    /**
     * 求最大深度 原理： 每层去找左右子节点的深度，直到当前节点为空时返回0
     * @param root 当前节点
     * @return 当前节点的深度
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * 求最小深度 原理:同上
     * @param root 当前节点
     * @return 当前节点的深度
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left > right ? right + 1 : left + 1;
    }

    /**
     * 判断两个二叉树是否是同一树
     * @param p 二叉树一
     * @param q 二叉树二
     * @return 是否相等
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || (p.val != q.val)) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 寻找最底层最左边的节点，广度优先遍历，只是每层从右往左遍历
     * @param root 根节点
     * @return bottomLeftValue
     */
    public TreeNode findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode current = null;
        while(!queue.isEmpty()){
            current = queue.poll();
            if (current.right != null) {
                queue.offer(current.right);
            }
            if (current.left != null) {
                queue.offer(current.left);
            }
        }
        return current;
    }

    /**
     * 普通树 查找最小祖先方法1,通过递归直接查找
     * @param root 根节点
     * @param p 节点1
     * @param q 节点2
     * @return 节点1和节点2的最小祖先
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 遍历到空叶子节点仍然没有找到，即返回null
        if(root == null){
            return null;
        }
        // 找到其中一个节点，返回该节点
        if(root == p || root == q){
           return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left,p,q);
        TreeNode right = lowestCommonAncestor1(root.right,p,q);
        // 当前节点的左右子树都找到，拿当前节点即是公共祖先
        if(left != null && right != null){
            return root;
        }
        // left != null  有可能left是其中一个目标节点或者就是两个目标节点的祖先
        return left != null ? left : right;
    }

    /**
     * 普通树 查找最小祖先方法2,求出路径后再比较路径得出
     * @param root 根节点
     * @param p 节点1
     * @param q 节点2
     * @return 节点1和节点2的最小祖先
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stackp = new Stack<TreeNode>();
        Stack<TreeNode> stackq = new Stack<TreeNode>();
        getPath(root, p, stackp);
        getPath(root, q, stackq);
        // 两个节点路径
        System.out.println(JSONObject.toJSONString(stackp));
        System.out.println(JSONObject.toJSONString(stackq));
        // 比较路径
        TreeNode ret = null;
        // 从根节点(栈后进先出)开始往前遍历，每一次pop() 即往下走一个层级
        // 遍历到某一层级的下个路径不再相等或其中任意一个stack为空了，则最小层级即为该层级
        while (!stackp.isEmpty() && !stackq.isEmpty() && stackp.peek().val == stackq.peek().val) {
            ret = stackp.peek();
            stackp.pop();
            stackq.pop();
        }
        return ret;
    }

    /**
     * 找到指定目标在树中的路径 原理：
     * 每层方法去寻找子节点下是否有目标节点，没有下层方法返回false，有返回true，
     * 本层方法按照返回的结果判断是否本层节点为目标的路径，是就添加到stack中
     * @param root 根节点
     * @param p 目标节点
     * @param stackp 目标节点路径
     * @return 是否找到目标节点
     */
    private static boolean getPath(TreeNode root, TreeNode p, Stack<TreeNode> stackp) {
        if (root == null)
            return false;
        if (root.val == p.val) {
            stackp.push(root);
            return true;
        } else {
            if (getPath(root.left, p, stackp) || getPath(root.right, p, stackp)) {
                stackp.push(root);
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是深度平衡树： 每层去寻找左右节点的最大深度，判断是否左右节点深度的绝对值大于1，是则返回false;
     * @param root 根节点
     * @return 是否是深度平衡树
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepath = maxDepth(root.left);
        int rightDepath = maxDepth(root.right);
        if (leftDepath - rightDepath > 1 || rightDepath - leftDepath > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    // 4
    // / \
    // 2 7
    // / \ / \
    // 1 3 6 9
    // 换成如下:
    // 4
    // / \
    // 7 2symmetric
    // / \ / \
    // 9 6 3 1
    /**
     * 置换二叉树的左右节点
     * @param root 根节点
     * @return 置换后根节点
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // 1
    // / \
    // 2 2
    // / \ / \
    // 3 4 4 3
    // 上面是，下面不是
    // 1
    // / \
    // 2 2
    // \ \
    // 3 3
    /**
     * 判断是否是为镜像树
     * @param root 根节点
     * @return 是否为镜像树
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    /**
     * 检查两个子树是否互为镜像树
     * 过程：两颗子树会递归的去判断
     *      子树1的左孩子 ?= 子树2的右孩子 && 子树2的右孩子 ?= 子树2的左孩子
     * @param t1 子树1
     * @param t2 子树2
     */
    public static boolean isSymmetric(TreeNode t1,TreeNode t2){
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if(t1.val == t2.val){
            return isSymmetric(t1.left,t2.right) && isSymmetric(t1.right,t2.left);
        }
        return false;
    }

//    Input:
//    Tree 1                     Tree 2
//            1                         2
//            / \                       / \
//            3   2                     1   3
//            /                           \   \
//            5                             4   7
//    Output:
//    Merged tree:
//            3
//            / \
//            4   5
//            / \   \
//            5   4   7
    /**
     * 合并两棵树，如果在相同部分两树的节点重叠了，就把节点值加起来，反之，放置某一个树的节点
     * @param t1 树1
     * @param t2 树2
     */
    public TreeNode mergeTrees(TreeNode t1,TreeNode t2){
        TreeNode ret = null;
        if(t1 != null && t2 != null){
            ret = new TreeNode(t1.val + t2.val);
            ret.left = mergeTrees(t1.left,t2.left);
            ret.right = mergeTrees(t1.right,t2.right);
            return ret;
        }
        if(t1 != null){// t1不为空,t2为空
            ret = new TreeNode(t1.val);
            ret.left = mergeTrees(t1.left,null);
            ret.right = mergeTrees(t1.right,null);
            return ret;
        }
        if(t2 != null){// t2不为空,t1为空
            ret = new TreeNode(t2.val);
            ret.left = mergeTrees(null,t2.left);
            ret.right = mergeTrees(null,t2.right);
            return ret;
        }
        return null;
    }


    public static void testScanNodes(){
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8,null,null,9};
        TreeNode root = createBinaryTree(array);
        BTreePrinter.printNode(root);
        System.out.println("先序遍历:" + scanNodes(root, "先序"));
        System.out.println("中序遍历:" + scanNodes(root, "中序"));
        System.out.println("后序遍历:" + scanNodes(root, "后序"));
        System.out.println("非递归前序遍历:" + JSONObject.toJSONString(preorder1(root)));
        System.out.println("非递归中序遍历:" + JSONObject.toJSONString(inorder(root)));
        System.out.println("非递归后序遍历:" + JSONObject.toJSONString(postOrder(root)));
        System.out.println("层级遍历：" + JSONObject.toJSONString(bfs(root)));
    }

    public static void testDept(){
        Integer[] array = {1,2,3,4,5,6,7,8,9};
        TreeNode root = createBinaryTree(array);
        BTreePrinter.printNode(root);
        System.out.println("最大深度:"+maxDepth(root));
        System.out.println("最小深度:"+minDepth(root));
        System.out.println("是否是深度平衡树:"+isBalanced(root));
    }

    public static void testLowestCommonAncestor(){
        Integer[] array = {1,2,3,4,5,6,7,8,9};
        TreeNode root = createBinaryTree(array);
        BTreePrinter.printNode(root);
        TreeNode res = lowestCommonAncestor2(root, new TreeNode(5), new TreeNode(6));
        System.out.println(res.val);
    }

    public static void testIsSymmetric(){
        Integer[] array = {1,2,3,3,null,2,null};
        TreeNode root = createBinaryTree(array);
        BTreePrinter.printNode(root);
        System.out.println(isSymmetric(root));
    }

    public static void main(String[] args) {
//        testDFSAndBFS();
        testScanNodes();
//        testDept();
//        testLowestCommonAncestor();
//        testIsSymmetric();
    }



}
