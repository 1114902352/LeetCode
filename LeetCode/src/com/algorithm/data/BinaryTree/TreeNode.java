package com.algorithm.data.BinaryTree;

import java.util.List;

//		A
//	   / \
//	  B   C
/**
 * 二叉树遍历 ，根是A，前序遍历就是ABC，中序遍历就是BAC，后序遍历就是BCA，根据A的位置决定
 * 遍历注意点：有子节点要先去找子节点，空要补足，再按上述顺序去找
 * 
 * 满二叉树的定义是除了叶子结点，其它结点左右孩子都有,深度为k的满二叉树，结点数就是2的k次方减1
 * 完全二叉树：除了最大的层次即成为一颗满二叉树且层次最大那层所有的结点均向左靠齐，即集中在左面的位置上，不能有空位置。
 *
 * 由递归顺序推出二次树时，无中序无法求得，中序可分离左右，前后序确定根节点
 */
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	/**
	 * 求最大深度 原理： 每层去找左右子节点的深度，直到当前节点为空时返回0
	 * 
	 * @param 当前节点
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
	 * 
	 * @param 当前节点
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
	 * 二叉树遍历
	 * 
	 * @param 当前节点
	 * @param 排序规则的标识
	 * @return
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
	 * 置换二叉树的左右节点
	 * 
	 * @param root
	 * @return
	 */
	// 4
	// / \
	// 2 7
	// / \ / \
	// 1 3 6 9
	// 换成如下:
	// 4
	// / \
	// 7 2
	// / \ / \
	// 9 6 3 1
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

	/**
	 * 判断两个二叉树是否是同一树
	 * 
	 * @param p
	 *            二叉树一
	 * @param q
	 *            二叉树二
	 * @return 是否相等
	 */
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null && q != null || q == null && p != null || p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	/**
	 * 二叉搜索树的最小祖先： 二叉查找树或者是一棵空树，或者是具有下列性质的二叉树：
	 * （1）若左子树不空，则左子树上所有结点的值均小于它的根结点的值；（2）若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
	 * （3）左、右子树也分别为二叉查找树； （4）没有键值相等的节点。 根据二叉树性质来查找
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q == null)
			return null;
		if (Math.max(p.val, q.val) < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		} else if (Math.min(p.val, q.val) > root.val) {
			return lowestCommonAncestor(root.right, p, q);
		} else
			return root;
	}

	/**
	 * 查找最小共同祖先方法2
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		// 发现目标节点则通过返回值标记该子树发现了某个目标结点
		if (root == null || root == p || root == q)
			return root;
		// 查看左子树中是否有目标结点，没有为null
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		// 查看右子树是否有目标节点，没有为null
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		// 都不为空，说明做右子树都有目标结点，则公共祖先就是本身
		if (left != null && right != null)
			return root;
		// 如果发现了目标节点，则继续向上标记为该目标节点
		return left == null ? right : left;
	}

	/**
	 * 找到指定目标在树中的路径 原理：
	 * 每层方法去寻找子节点下是否有目标节点，没有下层方法返回false，有返回true，本层方法按照返回的结果判断是否本层节点为目标的路径，
	 * 是就添加到list中
	 * 
	 * @param root
	 * @param target
	 * @param list
	 * @return
	 */
	public static boolean getPaths(TreeNode root, TreeNode target, List<TreeNode> list) {
		if (root == null) {
			return false;
		}
		if (root == target) {
			list.add(root);
			return true;
		}
		boolean leftBoolean = getPaths(root.left, target, list);
		boolean rightBoolean = getPaths(root.right, target, list);
		if (leftBoolean || rightBoolean) {
			list.add(root);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否是深度平衡树： 每层去寻找左右节点的最大深度，判断是否左右节点深度的绝对值大于1，是则返回false;
	 * 
	 * @param root
	 * @return
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
	 * 原理：将二叉树按照中序遍历并注意在每层返回结果上区分,例如二叉树{1,2,2,3,x,x,3},他的中序遍历应该为|||-3-|2-|1|-2|-3-|||
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		String str = scanNodes(root, "中序");
		System.out.println(str);
		if(str.length()%2==0)return false;
		char[] arr = str.toCharArray();
		int i=0,j=arr.length-1;
		while(i!=j){
			if(arr[i]!=arr[j]){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	/**
	 * 有标记的中序遍历
	 * @param root
	 * @return
	 */
	public static String scanNodes(TreeNode root){
		if(root==null){
			return "-";
		}
		scanNodes(root.left);
		return "|"+scanNodes(root.left)+root.val+scanNodes(root.right)+"|";
	}

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(2);
		TreeNode left2 = new TreeNode(3);
		TreeNode right2  = new TreeNode(3);
		root.left = left1;
		root.right = right1;
		left1.left = left2;
		right1.right = right2; 
		System.out.println(scanNodes(root));
	}

	// public static void main(String[] args) {
	// TreeNode root = new TreeNode(1);
	// TreeNode left1 = new TreeNode(2);
	// TreeNode left2 = new TreeNode(3);
	// TreeNode right1 = new TreeNode(4);
	// TreeNode right2 = new TreeNode(5);
	// // 创建一棵树
	// root.left = left1;
	// left1.right = left2;
	// root.right = right1;
	// right1.left = right2;
	// System.out.println("树的最大深度是：" + maxDepth(root));
	// System.out.println("树的最小深度是：" + minDepth(root));
	// System.out.println("先序遍历:" + scanNodes(root, "先序"));
	// System.out.println("中序遍历:" + scanNodes(root, "中序"));
	// System.out.println("后序遍历:" + scanNodes(root, "后序"));
	// invertTree(root);
	// System.out.println("置换之后先序遍历:" + scanNodes(root, "先序"));
	// TreeNode root1 = new TreeNode(0);
	// root1.left = root;
	// System.out.println("是否是相同树:" + isSameTree(root, root1));
	// List<TreeNode> list = new ArrayList<TreeNode>();
	// getPaths(root, right1, list);
	// for (TreeNode temp : list) {
	// System.out.println(temp.val);
	// }
	// }
}
