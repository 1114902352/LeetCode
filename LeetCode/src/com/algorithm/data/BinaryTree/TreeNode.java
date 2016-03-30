package com.algorithm.data.BinaryTree;

import java.util.List;

//		A
//	   / \
//	  B   C
/**
 * ���������� ������A��ǰ���������ABC�������������BAC�������������BCA������A��λ�þ���
 * ����ע��㣺���ӽڵ�Ҫ��ȥ���ӽڵ㣬��Ҫ���㣬�ٰ�����˳��ȥ��
 * 
 * ���������Ķ����ǳ���Ҷ�ӽ�㣬����������Һ��Ӷ���,���Ϊk���������������������2��k�η���1
 * ��ȫ���������������Ĳ�μ���Ϊһ�����������Ҳ������ǲ����еĽ��������룬�������������λ���ϣ������п�λ�á�
 *
 * �ɵݹ�˳���Ƴ�������ʱ���������޷���ã�����ɷ������ң�ǰ����ȷ�����ڵ�
 */
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	/**
	 * �������� ԭ�� ÿ��ȥ�������ӽڵ����ȣ�ֱ����ǰ�ڵ�Ϊ��ʱ����0
	 * 
	 * @param ��ǰ�ڵ�
	 * @return ��ǰ�ڵ�����
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
	 * ����С��� ԭ��:ͬ��
	 * 
	 * @param ��ǰ�ڵ�
	 * @return ��ǰ�ڵ�����
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
	 * ����������
	 * 
	 * @param ��ǰ�ڵ�
	 * @param �������ı�ʶ
	 * @return
	 */
	public static String scanNodes(TreeNode root, String flag) {
		if (root == null) {
			return " ";
		}
		if ("����".equals(flag)) {
			return root.val + scanNodes(root.left, flag) + scanNodes(root.right, flag);
		} else if ("����".equals(flag)) {
			return scanNodes(root.left, flag) + root.val + scanNodes(root.right, flag);
		} else {
			return scanNodes(root.left, flag) + scanNodes(root.right, flag) + root.val;
		}
	}

	/**
	 * �û������������ҽڵ�
	 * 
	 * @param root
	 * @return
	 */
	// 4
	// / \
	// 2 7
	// / \ / \
	// 1 3 6 9
	// ��������:
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
	 * �ж������������Ƿ���ͬһ��
	 * 
	 * @param p
	 *            ������һ
	 * @param q
	 *            ��������
	 * @return �Ƿ����
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
	 * ��������������С���ȣ� ���������������һ�ÿ����������Ǿ����������ʵĶ�������
	 * ��1�������������գ��������������н���ֵ��С�����ĸ�����ֵ����2�������������գ��������������н���ֵ�����ڻ�������ĸ�����ֵ��
	 * ��3����������Ҳ�ֱ�Ϊ����������� ��4��û�м�ֵ��ȵĽڵ㡣 ���ݶ���������������
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
	 * ������С��ͬ���ȷ���2
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		// ����Ŀ��ڵ���ͨ������ֵ��Ǹ�����������ĳ��Ŀ����
		if (root == null || root == p || root == q)
			return root;
		// �鿴���������Ƿ���Ŀ���㣬û��Ϊnull
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		// �鿴�������Ƿ���Ŀ��ڵ㣬û��Ϊnull
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		// ����Ϊ�գ�˵��������������Ŀ���㣬�򹫹����Ⱦ��Ǳ���
		if (left != null && right != null)
			return root;
		// ���������Ŀ��ڵ㣬��������ϱ��Ϊ��Ŀ��ڵ�
		return left == null ? right : left;
	}

	/**
	 * �ҵ�ָ��Ŀ�������е�·�� ԭ��
	 * ÿ�㷽��ȥѰ���ӽڵ����Ƿ���Ŀ��ڵ㣬û���²㷽������false���з���true�����㷽�����շ��صĽ���ж��Ƿ񱾲�ڵ�ΪĿ���·����
	 * �Ǿ���ӵ�list��
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
	 * �ж��Ƿ������ƽ������ ÿ��ȥѰ�����ҽڵ�������ȣ��ж��Ƿ����ҽڵ���ȵľ���ֵ����1�����򷵻�false;
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
	// �����ǣ����治��
	// 1
	// / \
	// 2 2
	// \ \
	// 3 3
	/**
	 * �ж��Ƿ���Ϊ������
	 * ԭ�����������������������ע����ÿ�㷵�ؽ��������,���������{1,2,2,3,x,x,3},�����������Ӧ��Ϊ|||-3-|2-|1|-2|-3-|||
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		String str = scanNodes(root, "����");
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
	 * �б�ǵ��������
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
	// // ����һ����
	// root.left = left1;
	// left1.right = left2;
	// root.right = right1;
	// right1.left = right2;
	// System.out.println("�����������ǣ�" + maxDepth(root));
	// System.out.println("������С����ǣ�" + minDepth(root));
	// System.out.println("�������:" + scanNodes(root, "����"));
	// System.out.println("�������:" + scanNodes(root, "����"));
	// System.out.println("�������:" + scanNodes(root, "����"));
	// invertTree(root);
	// System.out.println("�û�֮���������:" + scanNodes(root, "����"));
	// TreeNode root1 = new TreeNode(0);
	// root1.left = root;
	// System.out.println("�Ƿ�����ͬ��:" + isSameTree(root, root1));
	// List<TreeNode> list = new ArrayList<TreeNode>();
	// getPaths(root, right1, list);
	// for (TreeNode temp : list) {
	// System.out.println(temp.val);
	// }
	// }
}
